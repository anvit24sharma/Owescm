package com.owescm.client.model

data class LoginResponse(
    val data: Data,
    val message: String,
    val success: Int
)

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