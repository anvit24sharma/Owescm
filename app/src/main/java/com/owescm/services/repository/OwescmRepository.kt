package com.owescm.services.repository

import androidx.lifecycle.MutableLiveData
import com.owescm.client.model.*
import com.owescm.remote.OwescmRemoteEndPoint
import com.owescm.services.model.base.DataWrapper
import com.owescm.utils.NetworkUtils.makeNetworkCall
import okhttp3.MultipartBody
import okhttp3.RequestBody


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

    fun createErfx(erfxRequest: MutableMap<String, RequestBody?>, body: MultipartBody.Part): MutableLiveData<DataWrapper<CreateErfxResponseModel>> {
        return makeNetworkCall {
            retrofitCall = remoteApiEndPoint.createErfx(erfxRequest,body)
        }
    }

    fun getErfxLiveList(erfxLiveRequest: MutableMap<String, RequestBody?>): MutableLiveData<DataWrapper<ErfxLiveListResponse>> {
        return makeNetworkCall {
            retrofitCall = remoteApiEndPoint.getErfxLiveList(erfxLiveRequest)
        }
    }
    fun createEAuction(eAuctionLiveRequest: MutableMap<String, RequestBody?>): MutableLiveData<DataWrapper<CreateErfxResponseModel>> {
        return makeNetworkCall {
            retrofitCall = remoteApiEndPoint.createEAuction(eAuctionLiveRequest)
        }
    }

    fun getErfxSavedList(erfxSavedRequest: MutableMap<String, RequestBody?>): MutableLiveData<DataWrapper<ErfxSavedListResponse>> {
        return makeNetworkCall {
            retrofitCall = remoteApiEndPoint.getErfxSavedist(erfxSavedRequest)
        }
    }

    fun getErfxOpenPrimaryEvaluationList(openPrimaryEvaluationRequest: MutableMap<String, RequestBody?>): MutableLiveData<DataWrapper<OpenPrimaryEvaluationListResponse>> {
        return makeNetworkCall {
            retrofitCall = remoteApiEndPoint.getOpenPrimaryEvaluationList(openPrimaryEvaluationRequest)
        }
    }

    fun getPrimaryEvaluationDetails(openPrimaryEvaluationRequest: MutableMap<String, RequestBody?>): MutableLiveData<DataWrapper<PrimaryEvaluationDetailsResponse>> {
        return makeNetworkCall {
            retrofitCall = remoteApiEndPoint.getPrimaryEvaluationDetails(openPrimaryEvaluationRequest)
        }
    }

}