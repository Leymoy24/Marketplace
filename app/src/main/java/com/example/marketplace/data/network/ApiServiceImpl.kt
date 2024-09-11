package com.example.marketplace.data.network

import com.example.marketplace.data.model.request.LoginUserRequest
import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.model.response.GetProductResponse
import com.example.marketplace.data.model.response.LoginUserResponse
import com.example.marketplace.data.model.response.RegisterUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceImpl : ApiService {

    @POST(ApiRoutes.USER_REGISTER)
    override suspend fun registerUser(
        @Body registerUserRequest: RegisterUserRequest
    ): Response<RegisterUserResponse>

    @POST(ApiRoutes.USERS_AUTH_LOGIN)
    override suspend fun loginUser(
        @Body loginUserRequest: LoginUserRequest
    ): Response<LoginUserResponse>

    @GET(ApiRoutes.PRODUCTS)
    override suspend fun getProducts(): Response<GetProductResponse>

    @GET(ApiRoutes.PRODUCTS)
    override suspend fun getProductsByCategory(
        @Query("category") category: String
    ): Response<GetProductResponse>

}