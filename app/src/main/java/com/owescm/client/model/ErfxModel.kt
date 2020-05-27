package com.owescm.client.model


import com.google.gson.annotations.SerializedName

data class ErfxModel(
    @SerializedName("api_key")
    val apiKey: String,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("contractPeriodFrom")
    val contractPeriodFrom: String,
    @SerializedName("contractPeriodTo")
    val contractPeriodTo: String,
    @SerializedName("headline")
    val headline: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("payment_terms")
    val paymentTerms: String,
    @SerializedName("specialRequirement")
    val specialRequirement: String,
    @SerializedName("subCategory")
    val subCategory: String,
    @SerializedName("user_id")
    val userId: String
)