package com.example.marketplace.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.model.request.LoginUserRequest
import com.example.marketplace.data.network.ApiResult
import com.example.marketplace.domain.IsEmailCorrectUseCase
import com.example.marketplace.domain.LoginUserUseCase
import com.example.marketplace.ui.screen.CommonUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val isEmailCorrectUseCase: IsEmailCorrectUseCase,
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    val uiScreenState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val emailFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val passwordFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)

    private fun isEmailCorrect(email: String): Boolean {
        return isEmailCorrectUseCase.invoke(email)
    }

    fun login(email: String, password: String) {
        if (!isEmailCorrect(email = email)) {
            uiScreenState.value = AuthUiState.EmailNotCorrect
            emailFieldState.value = CommonUiState.Error
            return
        }

        viewModelScope.launch {
            uiScreenState.value = AuthUiState.Loading
            emailFieldState.value = CommonUiState.Initial
            passwordFieldState.value = CommonUiState.Initial

            val request = LoginUserRequest(
                email = email,
                password = password
            )

            when (val response = loginUserUseCase(loginUserRequest = request)) {
                is ApiResult.Success -> {
                    uiScreenState.value = AuthUiState.Success
                }

                is ApiResult.Error -> {
                    uiScreenState.value = AuthUiState.AuthFailed
                    emailFieldState.value = CommonUiState.Error
                    passwordFieldState.value = CommonUiState.Error
                }
            }
        }
    }
}