package com.owescm.remote


import com.owescm.supplier.model.CountModel
import com.owescm.supplier.model.LoginResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface OwescmRemoteEndPoint {


    @POST("/app/login")
    fun doLogin(@QueryMap loginRequest: Map<String, String>): Call<LoginResponse>

    @POST("app/supplier/getAllNumbers")
    fun getAllCounts(@QueryMap getAllCountRequest: Map<String, String>): Call<CountModel>

}