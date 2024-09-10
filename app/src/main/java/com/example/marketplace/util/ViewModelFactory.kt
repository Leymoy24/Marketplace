package com.example.marketplace.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.domain.IsEmailCorrectUseCase
import com.example.marketplace.ui.screen.registration.RegistrationViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val isEmailCorrectUseCase: IsEmailCorrectUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> {
                RegistrationViewModel(
                    isEmailCorrectUseCase = isEmailCorrectUseCase
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}