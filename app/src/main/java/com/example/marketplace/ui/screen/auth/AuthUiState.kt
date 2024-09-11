package com.example.marketplace.ui.screen.auth

sealed interface AuthUiState {
    data object Initial : AuthUiState
    data object Loading : AuthUiState
    data object Success : AuthUiState

    data object AuthFailed : AuthUiState
    data object EmptyFields : AuthUiState
    data object EmailNotCorrect : AuthUiState
}