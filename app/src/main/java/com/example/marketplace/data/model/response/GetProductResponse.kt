package com.example.marketplace.data.model.response

import com.example.marketplace.data.model.ProductUiModel

data class GetProductResponse(
    val count: Int,
    val Data: List<ProductUiModel>
)