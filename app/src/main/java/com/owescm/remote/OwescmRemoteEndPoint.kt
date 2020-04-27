package com.owescm.remote

import com.owescm.client.model.LoginRequest
import com.owescm.client.model.LoginResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface OwescmRemoteEndPoint {


    @POST("/app/login")
    fun doLogin(@QueryMap loginRequest: Map<String,String>): Call<LoginResponse>


}