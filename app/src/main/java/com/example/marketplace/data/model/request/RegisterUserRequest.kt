package com.example.marketplace.data.model.request

data class RegisterUserRequest(
    val name: String,
    val email: String,
    val password: String,
    val cpassword: String,
)