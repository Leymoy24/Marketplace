package com.example.marketplace.domain

import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.model.response.RegisterUserResponse
import com.example.marketplace.data.network.ApiResult

class RegisterUserUseCaseImpl(
    private val authRepository: AuthRepository
) : RegisterUserUseCase {
    override suspend fun invoke(registerUserRequest: RegisterUserRequest): ApiResult<RegisterUserResponse> {
        return authRepository.registerUser(registerUserRequest)
    }
}