package com.example.marketplace.domain.usecase

interface IsEmailCorrectUseCase {
    operator fun invoke(email: String): Boolean
}