package com.example.marketplace.domain

import com.example.marketplace.data.model.request.LoginUserRequest
import com.example.marketplace.data.model.response.LoginUserResponse
import com.example.marketplace.data.network.ApiResult

interface LoginUserUseCase {
    suspend operator fun invoke(loginUserRequest: LoginUserRequest): ApiResult<LoginUserResponse>
}