package com.owescm.client.model


import com.google.gson.annotations.SerializedName

data class ErfxSavedListResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("category_id")
        val categoryId: String,
        @SerializedName("contract_from")
        val contractFrom: String,
        @SerializedName("contract_to")
        val contractTo: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("eligible_supplier")
        val eligibleSupplier: Any,
        @SerializedName("erfx_doc")
        val erfxDoc: Any,
        @SerializedName("erfx_id")
        val erfxId: String,
        @SerializedName("erfx_status")
        val erfxStatus: String,
        @SerializedName("final_recommendation_reason")
        val finalRecommendationReason: Any,
        @SerializedName("generated_erfx_id")
        val generatedErfxId: Any,
        @SerializedName("headline")
        val headline: String,
        @SerializedName("initial_bid")
        val initialBid: Any,
        @SerializedName("location")
        val location: String,
        @SerializedName("payment_terms")
        val paymentTerms: String,
        @SerializedName("recommendation_reason")
        val recommendationReason: Any,
        @SerializedName("save")
        val save: String,
        @SerializedName("shortlisted_supplier")
        val shortlistedSupplier: Any,
        @SerializedName("special_requirement")
        val specialRequirement: String,
        @SerializedName("sub_category_id")
        val subCategoryId: String,
        @SerializedName("tnc")
        val tnc: Any,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("user_id")
        val userId: String
    )
}