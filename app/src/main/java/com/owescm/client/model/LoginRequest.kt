package com.owescm.client.model

data class LoginRequest(
    val email: String,
    val password: String,
    val user_type: String="1",
    val mobileNo: String
){}