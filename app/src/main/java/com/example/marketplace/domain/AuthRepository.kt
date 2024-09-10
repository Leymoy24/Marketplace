package com.example.marketplace.domain

interface AuthRepository {
    fun isEmailCorrect(email: String): Boolean
}