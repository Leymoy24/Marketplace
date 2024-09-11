package com.example.marketplace.domain.usecase

import com.example.marketplace.domain.repository.AuthRepository

class GetTokenUseCaseImpl(
    private val authRepository: AuthRepository
) : GetTokenUseCase {
    override fun invoke(): String = authRepository.getToken()
}