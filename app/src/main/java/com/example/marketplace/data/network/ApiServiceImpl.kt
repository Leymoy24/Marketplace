package com.example.marketplace.data.network

import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.model.response.RegisterUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceImpl : ApiService {

    @POST(ApiRoutes.USER_REGISTER)
    override suspend fun registerUser(
        @Body registerUserRequest: RegisterUserRequest
    ): Response<RegisterUserResponse>

}