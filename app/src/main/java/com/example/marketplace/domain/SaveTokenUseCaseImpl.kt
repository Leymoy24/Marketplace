package com.example.marketplace.domain

class SaveTokenUseCaseImpl(
    private val authRepository: AuthRepository
) : SaveTokenUseCase {
    override fun invoke(token: String) {
        authRepository.saveToken(token)
    }
}