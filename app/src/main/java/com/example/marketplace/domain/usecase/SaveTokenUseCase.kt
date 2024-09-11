package com.example.marketplace.domain.usecase

interface SaveTokenUseCase {
    operator fun invoke(token: String)
}