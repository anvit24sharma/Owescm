package com.owescm.supplier.model

data class LoginResponse(
    val data: Data,
    val message: String,
    val status: String
) {

    data class Data(
        val designation: String,
        val email: String,
        val emailVerifiedAt: String,
        val mobileNo: String,
        val name: String,
        val profilePhoto: String,
        val userVerifiedAt: String,
        val user_id: String
    )
}