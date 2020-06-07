package com.owescm.remote

import com.owescm.client.fragment.primaryevaluation.PrimaryEvaluationDetailsActivity
import com.owescm.client.model.*
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

    @Multipart
    @POST("/app/client/getErfxLiveList")
    fun getErfxLiveList(@PartMap erfxLiveRequest: MutableMap<String, RequestBody?>): Call<ErfxLiveListResponse>

    @Multipart
    @POST("/app/client/getErfxSavedList")
    fun getErfxSavedist(@PartMap erfxLiveRequest: MutableMap<String, RequestBody?>): Call<ErfxSavedListResponse>

    @Multipart
    @POST("/app/client/getOpenPrimaryEvaluationList")
    fun getOpenPrimaryEvaluationList(@PartMap erfxLiveRequest: MutableMap<String, RequestBody?>): Call<OpenPrimaryEvaluationListResponse>

    @Multipart
    @POST("/app/client/getPrimaryEvaluationOpenErfx")
    fun getPrimaryEvaluationDetails(@PartMap erfxLiveRequest: MutableMap<String, RequestBody?>): Call<PrimaryEvaluationDetailsResponse>
}
