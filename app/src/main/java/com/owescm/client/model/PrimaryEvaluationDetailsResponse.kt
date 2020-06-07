package com.owescm.client.model


import com.google.gson.annotations.SerializedName

data class PrimaryEvaluationDetailsResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("erfxReplies")
        val erfxReplies: List<ErfxReply>,
        @SerializedName("primaryEvaluationOpen")
        val primaryEvaluationOpen: List<PrimaryEvaluationOpen>
    ) {
        data class ErfxReply(
            @SerializedName("bid_doc_upload")
            val bidDocUpload: String,
            @SerializedName("category")
            val category: String,
            @SerializedName("commercial_ranking")
            val commercialRanking: Int = 0,
            @SerializedName("company_id")
            val companyId: String,
            @SerializedName("company_name")
            val companyName: String,
            @SerializedName("cover_headline")
            val coverHeadline: String,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("department")
            val department: Any,
            @SerializedName("designation")
            val designation: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("email_verified_at")
            val emailVerifiedAt: String,
            @SerializedName("erfx_id")
            val erfxId: String,
            @SerializedName("erfx_reply_id")
            val erfxReplyId: String,
            @SerializedName("final_recommendation_ranking")
            val finalRecommendationRanking: Int =0 ,
            @SerializedName("generated_erfx_id")
            val generatedErfxId: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("industry")
            val industry: String,
            @SerializedName("industry_served")
            val industryServed: String,
            @SerializedName("invited")
            val invited: Any,
            @SerializedName("invited_for")
            val invitedFor: Any,
            @SerializedName("location")
            val location: String,
            @SerializedName("location_served")
            val locationServed: String,
            @SerializedName("logo")
            val logo: String,
            @SerializedName("membership_type")
            val membershipType: String,
            @SerializedName("mobile_no")
            val mobileNo: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("password")
            val password: String,
            @SerializedName("payment_terms")
            val paymentTerms: String,
            @SerializedName("pincode")
            val pincode: String,
            @SerializedName("profile_photo")
            val profilePhoto: String,
            @SerializedName("quote")
            val quote: String,
            @SerializedName("recommendation_ranking")
            val recommendationRanking: String,
            @SerializedName("remarks")
            val remarks: String,
            @SerializedName("remember_token")
            val rememberToken: Any,
            @SerializedName("save")
            val save: String,
            @SerializedName("score")
            val score: Int = 0,
            @SerializedName("service_details")
            val serviceDetails: String,
            @SerializedName("shortlisted_supplier_id")
            val shortlistedSupplierId: String,
            @SerializedName("status")
            val status: String,
            @SerializedName("sub_category")
            val subCategory: String,
            @SerializedName("sub_user_type")
            val subUserType: String,
            @SerializedName("supplier_id")
            val supplierId: String,
            @SerializedName("suppliers_company_id")
            val suppliersCompanyId: String,
            @SerializedName("updated_at")
            val updatedAt: String,
            @SerializedName("user_id")
            val userId: String,
            @SerializedName("user_type")
            val userType: String,
            @SerializedName("user_verified_at")
            val userVerifiedAt: String,
            @SerializedName("valid_till")
            val validTill: String,
            @SerializedName("website")
            val website: String
        )

        data class PrimaryEvaluationOpen(
            @SerializedName("category_id")
            val categoryId: String,
            @SerializedName("client_id")
            val clientId: String,
            @SerializedName("company_id")
            val companyId: String,
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
            @SerializedName("sub_user_type")
            val subUserType: String,
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
}