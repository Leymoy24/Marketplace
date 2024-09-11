package com.example.marketplace.ui.screen.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplace.data.model.request.RegisterUserRequest
import com.example.marketplace.data.network.ApiResult
import com.example.marketplace.domain.usecase.IsEmailCorrectUseCase
import com.example.marketplace.domain.usecase.RegisterUserUseCase
import com.example.marketplace.ui.screen.CommonUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val isEmailCorrectUseCase: IsEmailCorrectUseCase,
    private val registerUserUseCase: RegisterUserUseCase
): ViewModel() {

    val uiScreenState = MutableStateFlow<RegistrationUiState>(RegistrationUiState.Initial)
    val nameFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val emailFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val passwordFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)
    val repeatPasswordFieldState = MutableStateFlow<CommonUiState>(CommonUiState.Initial)

    private fun isEmailCorrect(email: String): Boolean {
        return isEmailCorrectUseCase(email)
    }

    private fun isPasswordCorrect(password: String): Boolean {
        return password.length >= 8
    }

    fun register(name: String, email: String, password: String) {
        if (uiScreenState.value is RegistrationUiState.PasswordsNotEquals) return

        if (!isEmailCorrect(email)) {
            emailFieldState.value = CommonUiState.Error
            uiScreenState.value = RegistrationUiState.EmailNotCorrect
            return
        }

        if (!isPasswordCorrect(password)) {
            passwordFieldState.value = CommonUiState.Error
            repeatPasswordFieldState.value = CommonUiState.Error
            uiScreenState.value = RegistrationUiState.PasswordNotCorrect
            return
        }

        viewModelScope.launch {
            uiScreenState.value = RegistrationUiState.Loading

            val request = RegisterUserRequest(
                name = name,
                email = email,
                password = password,
                cpassword = password
            )

            when (val response = registerUserUseCase(registerUserRequest = request)) {
                is ApiResult.Success -> {
                    uiScreenState.value = if (response.data!!.status == STATUS_SUCCESS) {
                        RegistrationUiState.Success
                    } else {
                        RegistrationUiState.AlreadyRegisteredEmail
                    }
                }

                is ApiResult.Error -> {
                    uiScreenState.value = RegistrationUiState.AlreadyRegisteredEmail
                    emailFieldState.value = CommonUiState.Error
                }
            }
        }
    }

    private companion object {
        const val STATUS_SUCCESS = "success"
    }
}