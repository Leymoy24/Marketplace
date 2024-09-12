package com.example.marketplace.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.model.request.LoginUserRequest
import com.example.marketplace.data.network.ApiResult
import com.example.marketplace.domain.usecase.GetTokenUseCase
import com.example.marketplace.domain.usecase.IsEmailCorrectUseCase
import com.example.marketplace.domain.usecase.LoginUserUseCase
import com.example.marketplace.domain.usecase.SaveTokenUseCase
import com.example.marketplace.ui.screen.CommonUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val isEmailCorrectUseCase: IsEmailCorrectUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    private val _uiScreenState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val uiScreenState: StateFlow<AuthUiState> = _uiScreenState

    private val _emailFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val emailFieldState: StateFlow<CommonUiState> = _emailFieldState

    private val _passwordFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val passwordFieldState: StateFlow<CommonUiState> = _passwordFieldState

    fun changeUiScreenState(newState: AuthUiState) {
        _uiScreenState.value = newState
    }

    fun changeEmailFieldState(newState: CommonUiState) {
        _emailFieldState.value = newState
    }

    fun changePasswordFieldState(newState: CommonUiState) {
        _passwordFieldState.value = newState
    }

    private fun isEmailCorrect(email: String): Boolean {
        return isEmailCorrectUseCase(email)
    }

    fun login(email: String, password: String) {
        if (!isEmailCorrect(email = email)) {
            _uiScreenState.value = AuthUiState.EmailNotCorrect
            _emailFieldState.value = CommonUiState.Error
            return
        }

        viewModelScope.launch {
            _uiScreenState.value = AuthUiState.Loading
            _emailFieldState.value = CommonUiState.Initial
            _passwordFieldState.value = CommonUiState.Initial

            val request = LoginUserRequest(
                email = email,
                password = password
            )

            when (val response = loginUserUseCase(loginUserRequest = request)) {
                is ApiResult.Success -> {
                    saveTokenUseCase(response.data!!.token)
                    _uiScreenState.value = AuthUiState.Success
                }

                is ApiResult.Error -> {
                    _uiScreenState.value = AuthUiState.AuthFailed
                    _emailFieldState.value = CommonUiState.Error
                    _passwordFieldState.value = CommonUiState.Error
                }
            }
        }
    }

    suspend fun isTokenValid(): Boolean {
        _uiScreenState.value = AuthUiState.Loading
        val token = withContext(Dispatchers.IO) {
            getTokenUseCase()
        }
        return token != DEFAULT_STRING
    }

    private companion object {
        const val DEFAULT_STRING = "default"
    }
}