package com.owescm.client.model


import com.google.gson.annotations.SerializedName

data class CountModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
){
    data class Data(
        @SerializedName("eAuctionClosed")
        val eAuctionClosed: Int,
        @SerializedName("eAuctionToday")
        val eAuctionToday: Int,
        @SerializedName("eAuctionUpcoming")
        val eAuctionUpcoming: Int,
        @SerializedName("erfxClosed")
        val erfxClosed: Int,
        @SerializedName("erfxLive")
        val erfxLive: Int,
        @SerializedName("erfxSaved")
        val erfxSaved: Int,
        @SerializedName("finalEvaluationClosed")
        val finalEvaluationClosed: Int,
        @SerializedName("finalEvaluationOpen")
        val finalEvaluationOpen: Int,
        @SerializedName("primaryEvaluationClosed")
        val primaryEvaluationClosed: Int,
        @SerializedName("primaryEvaluationOpen")
        val primaryEvaluationOpen: Int
    )
}