package com.owescm.client.model


import com.google.gson.annotations.SerializedName

data class ErfxLiveListResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("erfx_id")
        val erfxId: String,
        @SerializedName("erfx_status")
        val erfxStatus: String,
        @SerializedName("generated_erfx_id")
        val generatedErfxId: Any,
        @SerializedName("headline")
        val headline: String,
        @SerializedName("location")
        val location: String,
        @SerializedName("service_sub_category_name")
        val serviceSubCategoryName: String
    )
}