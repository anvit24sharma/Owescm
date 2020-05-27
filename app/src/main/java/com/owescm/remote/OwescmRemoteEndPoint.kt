package com.owescm.remote

import com.owescm.client.model.CountModel
import com.owescm.client.model.ErfxModel
import com.owescm.client.model.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface OwescmRemoteEndPoint {


    @POST("/app/login")
    fun doLogin(@QueryMap loginRequest: Map<String,String>): Call<LoginResponse>

    @POST("app/client/getAllNumbers")
    fun getAllCounts(@QueryMap getAllCountRequest: Map<String,String>): Call<CountModel>

    @Multipart
    @POST("/app/client/erfxCreation")
    fun createErfx(@PartMap erfxModel: MutableMap<String, RequestBody?>, @Part body: MultipartBody.Part): Call<ErfxModel>
}