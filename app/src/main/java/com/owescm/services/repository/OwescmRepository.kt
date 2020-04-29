package com.owescm.services.repository

import androidx.lifecycle.MutableLiveData
import com.owescm.client.model.CountModel
import com.owescm.client.model.LoginResponse
import com.owescm.remote.OwescmRemoteEndPoint
import com.owescm.services.model.base.DataWrapper
import com.owescm.utils.NetworkUtils.makeNetworkCall


class OwescmRepository(private val remoteApiEndPoint: OwescmRemoteEndPoint) {

    fun doLogin(loginRequest: Map<String,String>): MutableLiveData<DataWrapper<LoginResponse>> {
        return makeNetworkCall {
            retrofitCall = remoteApiEndPoint.doLogin(loginRequest)
        }
    }

    fun getAllCounts(allCountRequest: Map<String,String>): MutableLiveData<DataWrapper<CountModel>> {
        return makeNetworkCall {
            retrofitCall = remoteApiEndPoint.getAllCounts(allCountRequest)
        }
    }


}