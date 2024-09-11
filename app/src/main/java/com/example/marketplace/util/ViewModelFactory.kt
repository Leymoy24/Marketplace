package com.example.marketplace.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.domain.IsEmailCorrectUseCase
import com.example.marketplace.domain.LoginUserUseCase
import com.example.marketplace.domain.RegisterUserUseCase
import com.example.marketplace.ui.screen.auth.AuthViewModel
import com.example.marketplace.ui.screen.registration.RegistrationViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val isEmailCorrectUseCase: IsEmailCorrectUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> {
                RegistrationViewModel(
                    isEmailCorrectUseCase = isEmailCorrectUseCase,
                    registerUserUseCase = registerUserUseCase
                ) as T
            }

            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(
                    isEmailCorrectUseCase = isEmailCorrectUseCase,
                    loginUserUseCase = loginUserUseCase
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}