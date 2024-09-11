package com.example.marketplace.data.source

import com.example.marketplace.data.model.ProductUiModel

class SessionStorage {
    var currentProduct: ProductUiModel? = null
    val listOfCategories: List<String> = listOf("computers", "bags", "furniture", "lamp", "clothing")
}