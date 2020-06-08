package com.owescm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.owescm.OwescmApplication.Companion.getOwescmRepository
import com.owescm.supplier.model.CountModel

class HomeViewModel : ViewModel() {

    lateinit var countsLiveData: LiveData<CountModel>
//    lateinit var createErfxLiveData:  LiveData<ErfxModel>
//    lateinit var erfxLiveListResponse:  LiveData<ErfxLiveListResponse>
//    lateinit var erfxSavedListResponse:  LiveData<ErfxSavedListResponse>
//    lateinit var openPrimaryEvaluationLiveListResponse:  LiveData<OpenPrimaryEvaluationListResponse>


    fun getAllCounts(getAllCountsRequest: HashMap<String, String>): LiveData<CountModel> {
        countsLiveData =
            Transformations.map(getOwescmRepository().getAllCounts(getAllCountsRequest)) {
                return@map it.responseData
            }
        return countsLiveData
    }

//    fun createErfx(erfxRequest: MutableMap<String, RequestBody?>, body: MultipartBody.Part) : LiveData<ErfxModel>{
//        createErfxLiveData = Transformations.map(getOwescmRepository().createErfx(erfxRequest,body)){
//            return@map it.responseData
//        }
//        return createErfxLiveData
//    }
//
//    fun getErfxLiveList(
//        getErfxLiveMap: MutableMap<String, RequestBody?>) : LiveData<ErfxLiveListResponse>{
//        erfxLiveListResponse = Transformations.map(getOwescmRepository().getErfxLiveList(getErfxLiveMap)){
//            return@map it.responseData
//        }
//        return erfxLiveListResponse
//    }
//
//    fun getErfxSavedList(
//        erfxSavedMap: MutableMap<String, RequestBody?>) : LiveData<ErfxSavedListResponse>{
//        erfxSavedListResponse = Transformations.map(getOwescmRepository().getErfxSavedList(erfxSavedMap)){
//            return@map it.responseData
//        }
//        return erfxSavedListResponse
//    }
//
//    fun getOpenPrimaryEvaluationList(
//        openPrimaryEvaluationeMap: MutableMap<String, RequestBody?>) : LiveData<OpenPrimaryEvaluationListResponse>{
//        openPrimaryEvaluationLiveListResponse = Transformations.map(getOwescmRepository().getErfxOpenPrimaryEvaluationList(openPrimaryEvaluationeMap)){
//            return@map it.responseData
//        }
//        return openPrimaryEvaluationLiveListResponse
//    }
}
