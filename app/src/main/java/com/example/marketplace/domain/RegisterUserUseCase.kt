package com.example.marketplace.domain

import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.model.response.RegisterUserResponse
import com.example.marketplace.data.network.ApiResult

interface RegisterUserUseCase {
    suspend fun invoke(registerUserRequest: RegisterUserRequest): ApiResult<RegisterUserResponse>
}