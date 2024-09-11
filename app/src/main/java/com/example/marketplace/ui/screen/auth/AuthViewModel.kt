package com.example.marketplace.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.model.request.LoginUserRequest
import com.example.marketplace.data.network.ApiResult
import com.example.marketplace.domain.GetTokenUseCase
import com.example.marketplace.domain.IsEmailCorrectUseCase
import com.example.marketplace.domain.LoginUserUseCase
import com.example.marketplace.domain.SaveTokenUseCase
import com.example.marketplace.ui.screen.CommonUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val isEmailCorrectUseCase: IsEmailCorrectUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    val uiScreenState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val emailFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val passwordFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)

    private fun isEmailCorrect(email: String): Boolean {
        return isEmailCorrectUseCase(email)
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
                    saveTokenUseCase(response.data!!.token)
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

    suspend fun isTokenValid(): Boolean {
        uiScreenState.value = AuthUiState.Loading
        val token = withContext(Dispatchers.IO) {
            getTokenUseCase()
        }
        return token != DEFAULT_STRING
    }

    private companion object {
        const val DEFAULT_STRING = "default"
    }
}