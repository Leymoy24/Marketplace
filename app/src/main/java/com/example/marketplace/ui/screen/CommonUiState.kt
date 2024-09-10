package com.example.marketplace.ui.screen

sealed interface CommonUiState {
    data object Initial : CommonUiState
    data object Error : CommonUiState
}