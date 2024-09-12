package com.example.marketplace.domain.repository

import com.example.marketplace.data.model.ProductUiModel
import com.example.marketplace.data.model.response.GetProductResponse
import com.example.marketplace.data.network.ApiResult

interface MainRepository {
    suspend fun getProducts(): ApiResult<GetProductResponse>
    suspend fun getProducts(limit: Int, page: Int): ApiResult<GetProductResponse>
    fun getCategories(): List<String>
    suspend fun getProductsByCategory(category: String): ApiResult<GetProductResponse>
    fun setCurrentProduct(product: ProductUiModel)
    fun getCurrentProduct(): ProductUiModel?
}