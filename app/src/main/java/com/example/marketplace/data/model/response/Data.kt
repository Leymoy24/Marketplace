package com.example.marketplace.data.model.response

data class Data(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val address: Address,
)