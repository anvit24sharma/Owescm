package com.owescm.client.model

import com.google.gson.annotations.SerializedName

data class InvitedSuppliersListModel(
    @SerializedName("supplierName")
    val supplierName: String,
    @SerializedName("contactPersonName")
    val contactPersonName: String,
    @SerializedName("supplierEmail")
    val supplierEmail: String,
    @SerializedName("supplierMobNumber")
    val supplierMobNumber: String


)