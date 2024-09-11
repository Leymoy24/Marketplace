package com.example.marketplace.domain.usecase

import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.model.response.RegisterUserResponse
import com.example.marketplace.data.network.ApiResult
import com.example.marketplace.domain.repository.AuthRepository

class RegisterUserUseCaseImpl(
    private val authRepository: AuthRepository
) : RegisterUserUseCase {
    override suspend operator fun invoke(registerUserRequest: RegisterUserRequest): ApiResult<RegisterUserResponse> {
        return authRepository.registerUser(registerUserRequest)
    }
}