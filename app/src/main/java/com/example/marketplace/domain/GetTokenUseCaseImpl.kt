package com.example.marketplace.domain

class GetTokenUseCaseImpl(
    private val authRepository: AuthRepository
) : GetTokenUseCase {
    override fun invoke(): String = authRepository.getToken()
}