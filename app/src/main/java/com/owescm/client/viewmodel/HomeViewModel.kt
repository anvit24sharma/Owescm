package com.owescm.client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.owescm.OwescmApplication.Companion.getOwescmRepository
import com.owescm.client.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody

class HomeViewModel :ViewModel(){

    lateinit var countsLiveData :LiveData<CountModel>
    lateinit var createErfxLiveData:  LiveData<CreateErfxResponseModel>
    lateinit var createEauctionLiveData:  LiveData<CreateErfxResponseModel>
    lateinit var erfxLiveListResponse:  LiveData<ErfxLiveListResponse>
    lateinit var erfxSavedListResponse:  LiveData<ErfxSavedListResponse>
    lateinit var openPrimaryEvaluationLiveListResponse:  LiveData<OpenPrimaryEvaluationListResponse>
    lateinit var primaryEvaluationDetailsLiveResponse:  LiveData<PrimaryEvaluationDetailsResponse>


    fun getAllCounts(getAllCountsRequest : HashMap<String,String>) : LiveData<CountModel>{
        countsLiveData = Transformations.map(getOwescmRepository().getAllCounts(getAllCountsRequest)){
            return@map it.responseData
        }
        return countsLiveData
    }

    fun createErfx(erfxRequest: MutableMap<String, RequestBody?>, body: MultipartBody.Part) : LiveData<CreateErfxResponseModel>{
        createErfxLiveData = Transformations.map(getOwescmRepository().createErfx(erfxRequest,body)){
            return@map it.responseData
        }
        return createErfxLiveData
    }

    fun getErfxLiveList(
        getErfxLiveMap: MutableMap<String, RequestBody?>) : LiveData<ErfxLiveListResponse>{
        erfxLiveListResponse = Transformations.map(getOwescmRepository().getErfxLiveList(getErfxLiveMap)){
            return@map it.responseData
        }
        return erfxLiveListResponse
    }

    fun createEAuction(
        getEauctionLiveMap: MutableMap<String, RequestBody?>) : LiveData<CreateErfxResponseModel>{
        createEauctionLiveData = Transformations.map(getOwescmRepository().createEAuction(getEauctionLiveMap)){
            return@map it.responseData
        }
        return createEauctionLiveData
    }

    fun getErfxSavedList(
        erfxSavedMap: MutableMap<String, RequestBody?>) : LiveData<ErfxSavedListResponse>{
        erfxSavedListResponse = Transformations.map(getOwescmRepository().getErfxSavedList(erfxSavedMap)){
            return@map it.responseData
        }
        return erfxSavedListResponse
    }

    fun getOpenPrimaryEvaluationList(
        openPrimaryEvaluationeMap: MutableMap<String, RequestBody?>) : LiveData<OpenPrimaryEvaluationListResponse>{
        openPrimaryEvaluationLiveListResponse = Transformations.map(getOwescmRepository().getErfxOpenPrimaryEvaluationList(openPrimaryEvaluationeMap)){
            return@map it.responseData
        }
        return openPrimaryEvaluationLiveListResponse
    }

    fun getPrimaryEvaluationDetails(
        openPrimaryEvaluationeMap: MutableMap<String, RequestBody?>) : LiveData<PrimaryEvaluationDetailsResponse>{
        primaryEvaluationDetailsLiveResponse = Transformations.map(getOwescmRepository().getPrimaryEvaluationDetails(openPrimaryEvaluationeMap)){
            return@map it.responseData
        }
        return primaryEvaluationDetailsLiveResponse
    }
}

