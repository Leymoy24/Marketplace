package com.example.marketplace.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marketplace.ui.screen.auth.AuthScreen
import com.example.marketplace.ui.screen.auth.AuthViewModel
import com.example.marketplace.ui.screen.loader.LoaderScreen
import com.example.marketplace.ui.screen.main.MainScreen
import com.example.marketplace.ui.screen.product.ProductScreen
import com.example.marketplace.ui.screen.registration.RegistrationScreen
import com.example.marketplace.ui.screen.registration.RegistrationViewModel
import com.example.marketplace.util.ViewModelFactory

object NavigationRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.AuthScreen)
}

@Composable
fun Navigation(
    navController: NavHostController,
    applicationContext: Context,
    activityContext: Context,
    viewModelFactory: ViewModelFactory
) {
    NavHost(navController = navController, startDestination = Screen.LoaderScreen.route) {
        composable(Screen.LoaderScreen.route) {
            NavigationRouter.currentScreen.value = Screen.LoaderScreen

            val authViewModel: AuthViewModel =
                ViewModelProvider(it, viewModelFactory)[AuthViewModel::class]

            LoaderScreen(
                onNavMainScreen = { navController.navigate(Screen.MainScreen.route) },
                onNavRegistrationScreen = { navController.navigate(Screen.RegistrationScreen.route) },
                viewModel = authViewModel
            )
        }

        composable(route = Screen.RegistrationScreen.route) {
            NavigationRouter.currentScreen.value = Screen.RegistrationScreen

            val registrationViewModel: RegistrationViewModel =
                ViewModelProvider(it, viewModelFactory)[RegistrationViewModel::class]

            RegistrationScreen(
                onEnterButtonPressed = { navController.navigate(Screen.AuthScreen.route) },
                viewModel = registrationViewModel
            )
        }

        composable(route = Screen.AuthScreen.route) {
            NavigationRouter.currentScreen.value = Screen.AuthScreen

            val authViewModel: AuthViewModel =
                ViewModelProvider(it, viewModelFactory)[AuthViewModel::class]

            AuthScreen(
                onEnterButtonPressed = { navController.navigate(Screen.MainScreen.route) },
                viewModel = authViewModel
            )
        }

        composable(route = Screen.MainScreen.route) {
            NavigationRouter.currentScreen.value = Screen.MainScreen
            MainScreen()
        }

        composable(route = Screen.ProductScreen.route) {
            NavigationRouter.currentScreen.value = Screen.ProductScreen
            ProductScreen()
        }
    }
}