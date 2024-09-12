package com.example.marketplace.ui.screen.product

import androidx.lifecycle.ViewModel
import com.example.marketplace.data.model.ProductUiModel
import com.example.marketplace.domain.repository.MainRepository

class ProductViewModel(
    private val repository: MainRepository
) : ViewModel() {

    fun getCurrentProduct(): ProductUiModel? {
        return repository.getCurrentProduct()
    }
}