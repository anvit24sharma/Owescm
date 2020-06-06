package com.owescm.client.model


import com.google.gson.annotations.SerializedName

data class OpenPrimaryEvaluationListResponse(
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
        @SerializedName("department")
        val department: Any,
        @SerializedName("designation")
        val designation: String,
        @SerializedName("eligible_supplier")
        val eligibleSupplier: Any,
        @SerializedName("email")
        val email: String,
        @SerializedName("email_verified_at")
        val emailVerifiedAt: String,
        @SerializedName("erfx_doc")
        val erfxDoc: String,
        @SerializedName("erfx_id")
        val erfxId: String,
        @SerializedName("erfx_status")
        val erfxStatus: String,
        @SerializedName("final_recommendation_reason")
        val finalRecommendationReason: Any,
        @SerializedName("generated_erfx_id")
        val generatedErfxId: String,
        @SerializedName("headline")
        val headline: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("initial_bid")
        val initialBid: Any,
        @SerializedName("location")
        val location: String,
        @SerializedName("mobile_no")
        val mobileNo: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("payment_terms")
        val paymentTerms: String,
        @SerializedName("profile_photo")
        val profilePhoto: String,
        @SerializedName("recommendation_reason")
        val recommendationReason: String,
        @SerializedName("remember_token")
        val rememberToken: Any,
        @SerializedName("save")
        val save: String,
        @SerializedName("shortlisted_supplier")
        val shortlistedSupplier: Any,
        @SerializedName("special_requirement")
        val specialRequirement: Any,
        @SerializedName("sub_category_id")
        val subCategoryId: String,
        @SerializedName("tnc")
        val tnc: Any,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("user_id")
        val userId: String,
        @SerializedName("user_type")
        val userType: String,
        @SerializedName("user_verified_at")
        val userVerifiedAt: String
    )
}