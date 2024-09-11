package com.example.marketplace.domain.repository

import com.example.marketplace.data.model.response.GetProductResponse
import com.example.marketplace.data.network.ApiResult

interface MainRepository {
    suspend fun getProducts(): ApiResult<GetProductResponse>
    fun getCategories(): List<String>
    suspend fun getProductsByCategory(category: String): ApiResult<GetProductResponse>
}