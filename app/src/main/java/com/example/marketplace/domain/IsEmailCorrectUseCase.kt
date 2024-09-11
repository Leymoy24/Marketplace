package com.example.marketplace.domain

interface IsEmailCorrectUseCase {
    operator fun invoke(email: String): Boolean
}