package com.example.marketplace.ui.screen.registration

sealed interface RegistrationUiState {
    data object Initial : RegistrationUiState
    data object Loading : RegistrationUiState
    data object Success : RegistrationUiState

    data object EmptyFields: RegistrationUiState
    data object EmailNotCorrect: RegistrationUiState
    data object AlreadyRegisteredEmail : RegistrationUiState
    data object PasswordNotCorrect : RegistrationUiState
    data object PasswordsNotEquals : RegistrationUiState
}