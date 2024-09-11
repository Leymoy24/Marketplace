package com.example.marketplace.domain

import com.example.marketplace.data.model.request.LoginUserRequest
import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.model.response.LoginUserResponse
import com.example.marketplace.data.model.response.RegisterUserResponse
import com.example.marketplace.data.network.ApiResult

interface AuthRepository {
    fun isEmailCorrect(email: String): Boolean
    suspend fun registerUser(registerUserRequest: RegisterUserRequest): ApiResult<RegisterUserResponse>
    suspend fun loginUser(loginUserRequest: LoginUserRequest): ApiResult<LoginUserResponse>
}