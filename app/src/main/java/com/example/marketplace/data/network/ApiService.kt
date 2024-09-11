package com.example.marketplace.data.network

import com.example.marketplace.data.model.request.LoginUserRequest
import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.model.response.GetProductResponse
import com.example.marketplace.data.model.response.LoginUserResponse
import com.example.marketplace.data.model.response.RegisterUserResponse
import retrofit2.Response

interface ApiService {
    suspend fun registerUser(registerUserRequest: RegisterUserRequest): Response<RegisterUserResponse>
    suspend fun loginUser(loginUserRequest: LoginUserRequest): Response<LoginUserResponse>
    suspend fun getProducts(): Response<GetProductResponse>
    suspend fun getProductsByCategory(category: String): Response<GetProductResponse>
}