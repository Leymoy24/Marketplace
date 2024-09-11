package com.example.marketplace.domain

interface SaveTokenUseCase {
    operator fun invoke(token: String)
}