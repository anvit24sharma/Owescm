package com.owescm.client.Fragment.ErfxFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.owescm.OwescmApplication
import com.owescm.OwescmApplication.Companion.apiKey
import com.owescm.OwescmApplication.Companion.userType
import com.owescm.client.R
import com.owescm.client.model.ErfxModel
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.utils.Constants
import kotlinx.android.synthetic.main.fragment_erfx_new.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class ErfxNewFragment : Fragment() {

    lateinit var homeViewModel  :HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_erfx_new, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        onClick()

    }

    private fun onClick() {
        btn_create.setOnClickListener {
            val file = File("/storage/emulated/0/Download/Corrections 6.jpg")
            val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            // MultipartBody.Part is used to send also the actual file name
            val body = MultipartBody.Part.createFormData("image", file.getName(), requestFile)
            // add another part within the multipart request
            val fullName = RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name")
            val erfxModel = ErfxModel(apiKey, userType,"Security","2020-07-24","2021-07-29","Anything", "Delhi", "ds",
                "ckn","ndjvndjk",
                OwescmApplication.prefs.getString(Constants.USER_ID, "-1")?:"-1")

           // homeViewModel.createErfx(erfxModel, body,fullName)
        }
    }

}
