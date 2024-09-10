package com.example.marketplace.ui.screen.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketplace.R
import com.example.marketplace.ui.composables.CommonButton
import com.example.marketplace.ui.composables.CommonOutlinedTextField
import com.example.marketplace.ui.composables.EyeIconButton
import com.example.marketplace.ui.screen.CommonUiState
import com.example.marketplace.ui.theme.Blue

@Composable
fun RegistrationScreen(
    onEnterButtonPressed: () -> Unit,
    viewModel: RegistrationViewModel,
) {
    val uiState by viewModel.uiScreenState.collectAsState()
    val nameFieldState by viewModel.nameFieldState.collectAsState()
    val emailFieldState by viewModel.emailFieldState.collectAsState()
    val passwordFieldState by viewModel.passwordFieldState.collectAsState()
    val repeatPasswordFieldState by viewModel.repeatPasswordFieldState.collectAsState()

    var nameTextState by rememberSaveable { mutableStateOf("") }
    var emailTextState by rememberSaveable { mutableStateOf("") }
    var passwordTextState by rememberSaveable { mutableStateOf("") }
    var repeatPasswordTextState by rememberSaveable { mutableStateOf("") }

    var isVisiblePassword by rememberSaveable { mutableStateOf(false) }
    var isVisibleRepeatPassword by rememberSaveable { mutableStateOf(false) }

    if (uiState is RegistrationUiState.Success) {
        onEnterButtonPressed()
    }

    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 48.dp, bottom = 70.dp),
                text = stringResource(R.string.registration),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )

            CommonOutlinedTextField(
                value = nameTextState,
                onValueChange = { value: String ->
                    nameTextState = value
                    viewModel.uiScreenState.value = RegistrationUiState.Initial
                    viewModel.nameFieldState.value = CommonUiState.Initial
                },
                textPlaceholder = stringResource(id = R.string.name),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text
                ),
                isError = nameFieldState is CommonUiState.Error
            )

            CommonOutlinedTextField(
                value = emailTextState,
                onValueChange = { value: String ->
                    emailTextState = value
                    viewModel.uiScreenState.value = RegistrationUiState.Initial
                    viewModel.emailFieldState.value = CommonUiState.Initial
                },
                modifier = Modifier
                    .padding(top = 22.dp),
                textPlaceholder = stringResource(id = R.string.email),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailFieldState is CommonUiState.Error
            )

            CommonOutlinedTextField(
                value = passwordTextState,
                onValueChange = { value: String ->
                    passwordTextState = value
                    if (passwordTextState != repeatPasswordTextState) {
                        viewModel.passwordFieldState.value = CommonUiState.Error
                        viewModel.repeatPasswordFieldState.value = CommonUiState.Error
                        viewModel.uiScreenState.value = RegistrationUiState.PasswordsNotEquals
                    } else {
                        viewModel.passwordFieldState.value = CommonUiState.Initial
                        viewModel.repeatPasswordFieldState.value = CommonUiState.Initial
                        viewModel.uiScreenState.value = RegistrationUiState.Initial
                    }
                },
                modifier = Modifier
                    .padding(top = 22.dp),
                textPlaceholder = stringResource(id = R.string.password),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                maxLength = 24,
                isError = passwordFieldState is CommonUiState.Error,
                visualTransformation = if (isVisiblePassword) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    EyeIconButton(
                        isVisibleEye = isVisiblePassword,
                        onCLick = {
                            isVisiblePassword = !isVisiblePassword
                        }
                    )
                }
            )

            CommonOutlinedTextField(
                value = repeatPasswordTextState,
                onValueChange = { value: String ->
                    repeatPasswordTextState = value
                    if (passwordTextState != repeatPasswordTextState) {
                        viewModel.passwordFieldState.value = CommonUiState.Error
                        viewModel.repeatPasswordFieldState.value = CommonUiState.Error
                        viewModel.uiScreenState.value = RegistrationUiState.PasswordsNotEquals
                    } else {
                        viewModel.passwordFieldState.value = CommonUiState.Initial
                        viewModel.repeatPasswordFieldState.value = CommonUiState.Initial
                        viewModel.uiScreenState.value = RegistrationUiState.Initial
                    }
                },
                modifier = Modifier
                    .padding(top = 22.dp),
                textPlaceholder = stringResource(id = R.string.confirm_the_password),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                maxLength = 24,
                isError = repeatPasswordFieldState is CommonUiState.Error,
                visualTransformation = if (isVisibleRepeatPassword) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    EyeIconButton(
                        isVisibleEye = isVisibleRepeatPassword,
                        onCLick = {
                            isVisibleRepeatPassword = !isVisibleRepeatPassword
                        }
                    )
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            CommonButton(
                text = stringResource(R.string.enter),
                isLoading = uiState is RegistrationUiState.Loading,
                onClick = {
                    if (nameTextState.isEmpty()) {
                        viewModel.nameFieldState.value = CommonUiState.Error
                    }
                    if (emailTextState.isEmpty()) {
                        viewModel.emailFieldState.value = CommonUiState.Error
                    }
                    if (passwordTextState.isEmpty()) {
                        viewModel.passwordFieldState.value = CommonUiState.Error
                    }
                    if (repeatPasswordTextState.isEmpty()) {
                        viewModel.repeatPasswordFieldState.value = CommonUiState.Error
                    }

                    if (nameTextState.isEmpty()
                        || emailTextState.isEmpty()
                        || passwordTextState.isEmpty()
                        || repeatPasswordTextState.isEmpty()
                    ) {
                        viewModel.uiScreenState.value = RegistrationUiState.EmptyFields
                        return@CommonButton
                    }

//                    viewModel.register(
//                        nickname = nameTextState,
//                        email = emailTextState,
//                        password = passwordTextState,
//                    )
                },
                modifier = Modifier.padding(bottom = 60.dp),
                color = Blue
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen({}, RegistrationViewModel())
}