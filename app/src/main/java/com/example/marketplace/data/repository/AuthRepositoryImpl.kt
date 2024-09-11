package com.example.marketplace.data.repository

import com.example.marketplace.data.model.request.LoginUserRequest
import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.model.response.LoginUserResponse
import com.example.marketplace.data.model.response.RegisterUserResponse
import com.example.marketplace.data.network.ApiResult
import com.example.marketplace.data.network.ApiService
import com.example.marketplace.data.source.RegexManager
import com.example.marketplace.data.source.sharedPref.SharedPref
import com.example.marketplace.domain.repository.AuthRepository
import retrofit2.Response

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val sharedPref: SharedPref
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

    override suspend fun loginUser(
        loginUserRequest: LoginUserRequest
    ): ApiResult<LoginUserResponse> {
        return try {
            val response: Response<LoginUserResponse> = apiService.loginUser(
                loginUserRequest = loginUserRequest
            )
            if (response.isSuccessful && response.body()?.status == "success") {
                ApiResult.Success(response.body())
            } else {
                ApiResult.Error(response.message())
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "unknown error")
        }
    }

    override fun getToken(): String {
        return sharedPref.getToken()
    }

    override fun saveToken(token: String) {
        sharedPref.saveToken(token)
    }

}