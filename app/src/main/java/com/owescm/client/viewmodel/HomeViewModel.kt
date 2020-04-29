package com.owescm.client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.owescm.OwescmApplication.Companion.getOwescmRepository
import com.owescm.client.model.CountModel

class HomeViewModel :ViewModel(){

    lateinit var countsLiveData :LiveData<CountModel>

    fun getAllCounts(getAllCountsRequest : HashMap<String,String>) : LiveData<CountModel>{
        countsLiveData = Transformations.map(getOwescmRepository().getAllCounts(getAllCountsRequest)){
           // if (it.responseData ==  null)
             //   return@map CountModel(CountModel.Data(0,0,0,0,0,0,0,0,0,0),"")
            return@map it.responseData
        }
        return countsLiveData
    }
}