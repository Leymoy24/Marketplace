package com.example.marketplace.ui.screen.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.network.ApiResult
import com.example.marketplace.domain.usecase.IsEmailCorrectUseCase
import com.example.marketplace.domain.usecase.RegisterUserUseCase
import com.example.marketplace.ui.screen.CommonUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val isEmailCorrectUseCase: IsEmailCorrectUseCase,
    private val registerUserUseCase: RegisterUserUseCase
): ViewModel() {

    private val _uiScreenState = MutableStateFlow<RegistrationUiState>(RegistrationUiState.Initial)
    val uiScreenState: StateFlow<RegistrationUiState> = _uiScreenState

    private val _nameFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val nameFieldState: StateFlow<CommonUiState> = _nameFieldState

    private val _emailFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val emailFieldState: StateFlow<CommonUiState> = _emailFieldState

    private val _passwordFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val passwordFieldState: StateFlow<CommonUiState> = _passwordFieldState

    private val _repeatPasswordFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val repeatPasswordFieldState: StateFlow<CommonUiState> = _repeatPasswordFieldState

    fun changeUiScreenState(newState: RegistrationUiState) {
        _uiScreenState.value = newState
    }

    fun changeNameFieldState(newState: CommonUiState) {
        _nameFieldState.value = newState
    }

    fun changeEmailFieldState(newState: CommonUiState) {
        _emailFieldState.value = newState
    }

    fun changePasswordFieldState(newState: CommonUiState) {
        _passwordFieldState.value = newState
    }

    fun changeRepeatPasswordFieldState(newState: CommonUiState) {
        _repeatPasswordFieldState.value = newState
    }

    private fun isEmailCorrect(email: String): Boolean {
        return isEmailCorrectUseCase(email)
    }

    private fun isPasswordCorrect(password: String): Boolean {
        return password.length >= 8
    }

    fun register(name: String, email: String, password: String) {
        if (uiScreenState.value is RegistrationUiState.PasswordsNotEquals) return

        if (!isEmailCorrect(email)) {
            _emailFieldState.value = CommonUiState.Error
            _uiScreenState.value = RegistrationUiState.EmailNotCorrect
            return
        }

        if (!isPasswordCorrect(password)) {
            _passwordFieldState.value = CommonUiState.Error
            _repeatPasswordFieldState.value = CommonUiState.Error
            _uiScreenState.value = RegistrationUiState.PasswordNotCorrect
            return
        }

        viewModelScope.launch {
            _uiScreenState.value = RegistrationUiState.Loading

            val request = RegisterUserRequest(
                name = name,
                email = email,
                password = password,
                cpassword = password
            )

            when (val response = registerUserUseCase(registerUserRequest = request)) {
                is ApiResult.Success -> {
                    _uiScreenState.value = if (response.data!!.status == STATUS_SUCCESS) {
                        RegistrationUiState.Success
                    } else {
                        RegistrationUiState.AlreadyRegisteredEmail
                    }
                }

                is ApiResult.Error -> {
                    _uiScreenState.value = RegistrationUiState.AlreadyRegisteredEmail
                    _emailFieldState.value = CommonUiState.Error
                }
            }
        }
    }

    private companion object {
        const val STATUS_SUCCESS = "success"
    }
}