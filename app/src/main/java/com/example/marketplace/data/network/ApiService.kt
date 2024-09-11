package com.example.marketplace.data.network

import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.model.response.RegisterUserResponse
import retrofit2.Response

interface ApiService {
    suspend fun registerUser(registerUserRequest: RegisterUserRequest):Response<RegisterUserResponse>
}