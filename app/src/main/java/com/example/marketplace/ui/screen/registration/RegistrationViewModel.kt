package com.example.marketplace.ui.screen.registration

import androidx.lifecycle.ViewModel
import com.example.marketplace.domain.IsEmailCorrectUseCase
import com.example.marketplace.ui.screen.CommonUiState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val isEmailCorrectUseCase: IsEmailCorrectUseCase
): ViewModel() {
    val uiScreenState = MutableStateFlow<RegistrationUiState>(RegistrationUiState.Initial)
    val nameFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val emailFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val passwordFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val repeatPasswordFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)

    fun isEmailCorrect(email: String): Boolean {
        return isEmailCorrectUseCase.invoke(email)
    }
}