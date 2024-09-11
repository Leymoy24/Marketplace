package com.example.marketplace.domain.usecase

import com.example.marketplace.domain.repository.AuthRepository

class IsEmailCorrectUseCaseImpl(
    private val authRepository: AuthRepository
) : IsEmailCorrectUseCase {
    override operator fun invoke(email: String): Boolean {
        return authRepository.isEmailCorrect(email)
    }
}