package com.example.marketplace.navigation

sealed class Screen(val route: String, val title: String) {
    data object RegistrationScreen : Screen(route = "registration_screen", title = "Registration")
    data object AuthScreen : Screen(route = "auth_screen", title = "Auth")
    data object MainScreen : Screen(route = "main_screen", title = "Main")
    data object ProductScreen : Screen(route = "product_screen", title = "Product")
}
