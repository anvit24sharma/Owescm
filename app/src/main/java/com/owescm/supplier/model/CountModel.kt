package com.owescm.supplier.model


import com.google.gson.annotations.SerializedName

data class CountModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("eAuctionClosed")
        val eAuctionClosed: Int,
        @SerializedName("eAuctionToday")
        val eAuctionToday: Int,
        @SerializedName("eAuctionUpcoming")
        val eAuctionUpcoming: Int,
        @SerializedName("ebidNew")
        val ebidNew: Int,
        @SerializedName("ebidClosed")
        val ebidClosed: Int,
        @SerializedName("ebidLive")
        val ebidLive: Int,
        @SerializedName("ebidSaved")
        val ebidSaved: Int
//        @SerializedName("finalEvaluationClosed")
//        val finalEvaluationClosed: Int,
//        @SerializedName("finalEvaluationOpen")
//        val finalEvaluationOpen: Int,
//        @SerializedName("primaryEvaluationClosed")
//        val primaryEvaluationClosed: Int,
//        @SerializedName("primaryEvaluationOpen")
//        val primaryEvaluationOpen: Int
    )
}