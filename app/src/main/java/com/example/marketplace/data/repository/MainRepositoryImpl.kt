package com.example.marketplace.data.repository

import com.example.marketplace.data.model.response.GetProductResponse
import com.example.marketplace.data.network.ApiResult
import com.example.marketplace.data.network.ApiService
import com.example.marketplace.data.source.SessionStorage
import com.example.marketplace.data.source.sharedPref.SharedPref
import com.example.marketplace.domain.repository.MainRepository

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage,
    private val sharedPref: SharedPref
) : MainRepository {

    override suspend fun getProducts(): ApiResult<GetProductResponse> {
        return try {
            val response = apiService.getProducts()
            if (response.isSuccessful) {
                ApiResult.Success(response.body())
            } else {
                ApiResult.Error(response.message())
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "unknown error")
        }
    }

    override fun getCategories(): List<String> {
        return sessionStorage.listOfCategories
    }

    override suspend fun getProductsByCategory(category: String): ApiResult<GetProductResponse> {
        return try {
            val response = apiService.getProductsByCategory(category)
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