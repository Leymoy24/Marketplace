package com.example.marketplace.domain.usecase

import com.example.marketplace.domain.repository.AuthRepository

class SaveTokenUseCaseImpl(
    private val authRepository: AuthRepository
) : SaveTokenUseCase {
    override fun invoke(token: String) {
        authRepository.saveToken(token)
    }
}