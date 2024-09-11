package com.example.marketplace.domain

import com.example.marketplace.data.model.request.LoginUserRequest
import com.example.marketplace.data.model.response.LoginUserResponse
import com.example.marketplace.data.network.ApiResult

class LoginUserUseCaseImpl(
    private val authRepository: AuthRepository
) : LoginUserUseCase {
    override suspend operator fun invoke(loginUserRequest: LoginUserRequest): ApiResult<LoginUserResponse> {
        return authRepository.loginUser(loginUserRequest)
    }
}