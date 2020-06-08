package com.owescm.services.repository

import androidx.lifecycle.MutableLiveData
import com.owescm.remote.OwescmRemoteEndPoint
import com.owescm.services.model.base.DataWrapper
import com.owescm.supplier.model.CountModel
import com.owescm.supplier.model.LoginResponse
import com.owescm.utils.NetworkUtils


class OwescmRepository(private val remoteApiEndPoint: OwescmRemoteEndPoint) {


    fun doLogin(loginRequest: Map<String, String>): MutableLiveData<DataWrapper<LoginResponse>> {
        return NetworkUtils.makeNetworkCall {
            retrofitCall = remoteApiEndPoint.doLogin(loginRequest)
        }
    }

    fun getAllCounts(allCountRequest: Map<String, String>): MutableLiveData<DataWrapper<CountModel>> {
        return NetworkUtils.makeNetworkCall {
            retrofitCall = remoteApiEndPoint.getAllCounts(allCountRequest)
        }
    }

}