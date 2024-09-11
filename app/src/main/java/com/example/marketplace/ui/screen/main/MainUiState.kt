package com.example.marketplace.ui.screen.main

interface MainUiState {
    data object Initial : MainUiState
    data object Loading : MainUiState
    data class Success<out T>(val data: T) : MainUiState
    data class Error(val message: String) : MainUiState
}

sealed interface CategoriesUiState{
    data object Initial: CategoriesUiState
    data object Loading: CategoriesUiState
    data class Success<out T>(val data: T): CategoriesUiState
    data class Error(val message: String): CategoriesUiState
}