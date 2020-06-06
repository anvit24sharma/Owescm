package com.owescm.utils

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import java.net.URISyntaxException

class FileUtils{

    companion object {
        @SuppressLint("NewApi", "Recycle")
        @Throws(URISyntaxException::class)
        fun getPath(context: Context, uri: Uri): String? {
            var documentUri = uri
            var selection: String? = null
            var selectionArgs: Array<String>? = null
            if (DocumentsContract.isDocumentUri(context, documentUri)) {
                when {
                    isExternalStorageDocument(documentUri) -> {
                        val docId = DocumentsContract.getDocumentId(documentUri)
                        val split =
                            docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                    }
                    isDownloadsDocument(documentUri) -> {
                        val id = DocumentsContract.getDocumentId(documentUri)
                        documentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"),
                            java.lang.Long.valueOf(id)
                        )
                    }
                    isMediaDocument(documentUri) -> {
                        val docId = DocumentsContract.getDocumentId(documentUri)
                        val split =
                            docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        when (split[0]) {
                            "image" -> documentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            "video" -> documentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                            "audio" -> documentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                        }
                        selection = "_id=?"
                        selectionArgs = arrayOf(split[1])
                    }
                }
            }
            if ("content".equals(documentUri.scheme, ignoreCase = true)) {
                val projection = arrayOf(MediaStore.Images.Media.DATA)
                val cursor: Cursor?
                try {
                    cursor = context.contentResolver
                        .query(documentUri, projection, selection, selectionArgs, null)
                    val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    if (cursor.moveToFirst()) {
                        return cursor.getString(columnIndex)
                    }
                } catch (e: Exception) {
                }

            } else if ("file".equals(documentUri.scheme, ignoreCase = true)) {
                return documentUri.path
            }
            return null
        }


        private fun isExternalStorageDocument(uri: Uri): Boolean {
            return "com.android.externalstorage.documents" == uri.authority
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is DownloadsProvider.
         */
        private fun isDownloadsDocument(uri: Uri): Boolean {
            return "com.android.providers.downloads.documents" == uri.authority
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is MediaProvider.
         */
        private fun isMediaDocument(uri: Uri): Boolean {
            return "com.android.providers.media.documents" == uri.authority
        }
    }
}