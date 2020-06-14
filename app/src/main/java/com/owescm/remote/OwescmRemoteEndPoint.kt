package com.owescm.remote

import com.owescm.client.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface OwescmRemoteEndPoint {


    @POST("/app/login")
    fun doLogin(@QueryMap loginRequest: Map<String,String>): Call<LoginResponse>

    @POST("app/client/getAllNumbers")
    fun getAllCounts(@QueryMap getAllCountRequest: Map<String,String>): Call<CountModel>

    @Multipart
    @POST("/app/client/erfxCreation")
    fun createErfx(@PartMap erfxModel: MutableMap<String, RequestBody?>, @Part body: MultipartBody.Part): Call<CreateErfxResponseModel>

    @Multipart
    @POST("/app/client/getErfxLiveList")
    fun getErfxLiveList(@PartMap erfxLiveRequest: MutableMap<String, RequestBody?>): Call<ErfxLiveListResponse>

    @Multipart
    @POST("/app/client/createEauction")
    fun createEAuction(@PartMap eAuctionMap: MutableMap<String, RequestBody?>): Call<CreateErfxResponseModel>


    @Multipart
    @POST("/app/client/getErfxSavedList")
    fun getErfxSavedist(@PartMap erfxLiveRequest: MutableMap<String, RequestBody?>): Call<ErfxSavedListResponse>

    @Multipart
    @POST("/app/client/getOpenPrimaryEvaluationList")
    fun getOpenPrimaryEvaluationList(@PartMap erfxLiveRequest: MutableMap<String, RequestBody?>): Call<OpenPrimaryEvaluationListResponse>

    @Multipart
    @POST("/app/client/getPrimaryEvaluationOpenErfx")
    fun getPrimaryEvaluationDetails(@PartMap erfxLiveRequest: MutableMap<String, RequestBody?>): Call<PrimaryEvaluationDetailsResponse>

    @GET("images/profile_photo/test client_15892231228822.jpg")
    @Streaming
    fun downloadFile(): Call<ResponseBody?>?
}
