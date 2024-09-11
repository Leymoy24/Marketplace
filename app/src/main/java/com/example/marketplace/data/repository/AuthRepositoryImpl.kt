package com.example.marketplace.data.repository

import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.model.response.RegisterUserResponse
import com.example.marketplace.data.network.ApiResult
import com.example.marketplace.data.network.ApiService
import com.example.marketplace.data.source.RegexManager
import com.example.marketplace.domain.AuthRepository
import retrofit2.Response

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {

    override fun isEmailCorrect(email: String): Boolean {
        return RegexManager.isEmailCorrect(email = email)
    }

    override suspend fun registerUser(
        registerUserRequest: RegisterUserRequest
    ): ApiResult<RegisterUserResponse> {
        return try {
            val response: Response<RegisterUserResponse> = apiService.registerUser(
                registerUserRequest = registerUserRequest
            )
            if (response.isSuccessful) {
                ApiResult.Success(response.body())
            } else {
                ApiResult.Error(response.message())
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "unknown error")
        }
    }

}