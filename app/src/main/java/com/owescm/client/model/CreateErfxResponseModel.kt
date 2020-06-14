package com.owescm.client.model

import com.google.gson.annotations.SerializedName

data class CreateErfxResponseModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)
