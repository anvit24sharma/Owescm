package com.owescm.client.Fragment.ErfxFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.owescm.OwescmApplication
import com.owescm.OwescmApplication.Companion.apiKey
import com.owescm.OwescmApplication.Companion.userType
import com.owescm.client.R
import com.owescm.client.viewmodel.HomeViewModel
import com.owescm.utils.Constants
import com.owescm.utils.Constants.Companion.toRequestBody
import okhttp3.RequestBody
import java.util.HashMap


class ErfxLiveFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_erfx_live, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val map: MutableMap<String, RequestBody?> = HashMap()
        map["api_key"] = toRequestBody(apiKey)
        map["user_type"] = toRequestBody(userType)
        map["user_id"] = toRequestBody(OwescmApplication.prefs.getString(Constants.USER_ID, "-1") ?: "-1")

        homeViewModel.getErfxLiveList(map)
    }
}
