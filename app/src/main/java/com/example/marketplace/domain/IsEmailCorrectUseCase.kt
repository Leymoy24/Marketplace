package com.example.marketplace.domain

interface IsEmailCorrectUseCase {
    fun invoke(email: String): Boolean
}