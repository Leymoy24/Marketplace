package com.example.marketplace.data.model

data class ProductUiModel(
    val name: String,
    val description: String?,
    val category: List<String?>,
    val price: Int,
    val discounted_price: Int?,
    val images: List<String?>
)
