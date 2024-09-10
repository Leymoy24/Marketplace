package com.example.marketplace.domain

class IsEmailCorrectUseCaseImpl(
    private val authRepository: AuthRepository
) : IsEmailCorrectUseCase {
    override fun invoke(email: String): Boolean {
        return authRepository.isEmailCorrect(email)
    }
}