package com.example.marketplace.ui.screen.auth

import androidx.lifecycle.ViewModel
import com.example.marketplace.domain.IsEmailCorrectUseCase
import com.example.marketplace.ui.screen.CommonUiState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val isEmailCorrectUseCase: IsEmailCorrectUseCase
): ViewModel() {
    val uiScreenState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val emailFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val passwordFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)

    fun isEmailCorrect(email: String): Boolean {
        return isEmailCorrectUseCase.invoke(email)
    }
}