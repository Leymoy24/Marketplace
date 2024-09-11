package com.example.marketplace.ui.screen.loader

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.marketplace.ui.screen.auth.AuthViewModel
import com.example.marketplace.ui.theme.Blue
import kotlinx.coroutines.launch

@Composable
fun LoaderScreen(
    onNavMainScreen: () -> Unit,
    onNavRegistrationScreen: () -> Unit,
    viewModel: AuthViewModel
) {
    var isValidToken by remember { mutableStateOf<Boolean?>(null) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            isValidToken = viewModel.isTokenValid()
        }
    }

    when (isValidToken) {
        null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Blue)
            }
        }

        true -> {
            LaunchedEffect(Unit) {
                onNavMainScreen()
            }
        }

        false -> {
            LaunchedEffect(Unit) {
                onNavRegistrationScreen()
            }
        }
    }
}