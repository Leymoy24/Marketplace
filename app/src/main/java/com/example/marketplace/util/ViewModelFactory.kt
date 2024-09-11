package com.example.marketplace.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.domain.repository.MainRepository
import com.example.marketplace.domain.usecase.GetTokenUseCase
import com.example.marketplace.domain.usecase.IsEmailCorrectUseCase
import com.example.marketplace.domain.usecase.LoginUserUseCase
import com.example.marketplace.domain.usecase.RegisterUserUseCase
import com.example.marketplace.domain.usecase.SaveTokenUseCase
import com.example.marketplace.ui.screen.auth.AuthViewModel
import com.example.marketplace.ui.screen.main.MainViewModel
import com.example.marketplace.ui.screen.registration.RegistrationViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val isEmailCorrectUseCase: IsEmailCorrectUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val mainRepository: MainRepository
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
                    loginUserUseCase = loginUserUseCase,
                    getTokenUseCase = getTokenUseCase,
                    saveTokenUseCase = saveTokenUseCase
                ) as T
            }

            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository = mainRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}