package com.owescm.client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.owescm.OwescmApplication.Companion.getOwescmRepository
import com.owescm.client.model.CountModel
import com.owescm.client.model.ErfxModel
import okhttp3.MultipartBody
import okhttp3.RequestBody

class HomeViewModel :ViewModel(){

    lateinit var countsLiveData :LiveData<CountModel>
    lateinit var createErfxLiveData:  LiveData<ErfxModel>

    fun getAllCounts(getAllCountsRequest : HashMap<String,String>) : LiveData<CountModel>{
        countsLiveData = Transformations.map(getOwescmRepository().getAllCounts(getAllCountsRequest)){
            return@map it.responseData
        }
        return countsLiveData
    }

    fun createErfx(
        erfxRequest: MutableMap<String, RequestBody?>,
        body: MultipartBody.Part
    ) : LiveData<ErfxModel>{
        createErfxLiveData = Transformations.map(getOwescmRepository().createErfx(erfxRequest,body)){
            return@map it.responseData
        }
        return createErfxLiveData
    }
}
