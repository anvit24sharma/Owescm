package com.owescm.utils

import okhttp3.MediaType
import okhttp3.RequestBody

class Constants {
    companion object {

        const val APP_NAME = "Owescm"
        const val IS_LOGGED_IN = "isLoggedIn"
        const val USER_TOKEN = "userToken"
        const val PREFS = "Owescm Prefs"
        const val EMAIL = "email"
        const val PROFILE_IMAGE = "image"
        const val MOBILE_NUMBER = "mobile"
        const val DESIGNATION = "designation"
        const val USER_ID = "user_id"
        const val BASE_URL = "https://owescm.com"

        fun toRequestBody(value: String?): RequestBody? {
            return RequestBody.create(MediaType.parse("multipart/form-data"), value)
        }
    }
}