package com.example.marketplace.ui.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.model.ProductUiModel
import com.example.marketplace.data.network.ApiResult
import com.example.marketplace.domain.repository.MainRepository
import com.example.marketplace.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _uiScreenState = MutableStateFlow<MainUiState>(MainUiState.Initial)
    val uiScreenState: StateFlow<MainUiState> = _uiScreenState

    private val _categoriesUiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Initial)
    val categoriesUiState: StateFlow<CategoriesUiState> = _categoriesUiState

    private val _listOfProducts = MutableStateFlow<MutableList<ProductUiModel>?>(null)
    val listOfProducts: StateFlow<List<ProductUiModel>?> = _listOfProducts

    private val _listOfSearchedProducts = MutableStateFlow<MutableList<ProductUiModel>?>(null)
    val listOfSearchedProducts: StateFlow<List<ProductUiModel>?> = _listOfSearchedProducts

    private val _listOfCategories = MutableStateFlow<List<String>?>(null)
    val listOfCategories: StateFlow<List<String>?> = _listOfCategories

    private val _currentCategory = MutableStateFlow<String?>(null)
    val currentCategory: StateFlow<String?> = _currentCategory

    private var currentPage = 0

    init {
        getAllCategories()
        getAllProducts()
    }

    fun changeMainUiState(newState: MainUiState) {
        _uiScreenState.value = newState
    }

    fun changeCategoriesUiState(newState: CategoriesUiState) {
        _categoriesUiState.value = newState
    }

    fun getAllCategories() {
        _categoriesUiState.value = CategoriesUiState.Loading

        _categoriesUiState.value = CategoriesUiState.Success(repository.getCategories())
        _listOfCategories.value = repository.getCategories()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            _uiScreenState.value = MainUiState.Loading

            when (val result = repository.getProducts()) {
                is ApiResult.Success -> {
                    _uiScreenState.value = MainUiState.Success(result.data)
                    Log.i("YYY", result.data?.Data.toString())
                    _listOfProducts.value = result.data!!.Data.toMutableList()
                    currentPage++
                }

                is ApiResult.Error -> {
                    _uiScreenState.value = MainUiState.Error(result.error)
                }
            }
        }
    }

    fun getMoreProducts(category: String? = null) {
        viewModelScope.launch {
            _uiScreenState.value = MainUiState.Loading

            when (val result = repository.getProducts(
//                skip = currentPage * Constants.LIMIT_PRODUCTS,
//                category = category
            )) {
                is ApiResult.Success -> {
                    _uiScreenState.value = MainUiState.Success(result.data)

                    if (category != null) {
                        _listOfSearchedProducts.value?.addAll(result.data!!.Data)
                    } else {
                        _listOfProducts.value?.addAll(result.data!!.Data)
                    }

                    if (result.data!!.Data.isNotEmpty()) {
                        currentPage++
                    }
                }

                is ApiResult.Error -> {
                    _uiScreenState.value = MainUiState.Error(result.error)
                }
            }
        }
    }

    fun searchProductsByCategory(category: String) {
        currentPage = 0
        viewModelScope.launch {
            _categoriesUiState.value = CategoriesUiState.Loading

            when (val result = repository.getProductsByCategory(category = category)) {
                is ApiResult.Success -> {
                    _categoriesUiState.value = CategoriesUiState.Success(result.data)
                    _currentCategory.value = category
                    _listOfSearchedProducts.value = result.data!!.Data.toMutableList()

                    if (result.data.Data.isNotEmpty()) {
                        currentPage++
                    }

                }

                is ApiResult.Error -> {
                    _categoriesUiState.value = CategoriesUiState.Error(result.error)
                }
            }
        }
    }

    fun setCurrentProduct(newProduct: ProductUiModel) {
        repository.setCurrentProduct(newProduct)
    }

    fun clearListOfSearchedProducts() {
        _listOfSearchedProducts.value = null
    }

    fun clearCurrentCategory() {
        _currentCategory.value = null
    }
}