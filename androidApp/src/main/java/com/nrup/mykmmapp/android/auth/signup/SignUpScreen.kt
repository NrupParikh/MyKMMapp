package com.nrup.mykmmapp.android.auth.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.nrup.mykmmapp.android.common.theming.ButtonHeight
import com.nrup.mykmmapp.android.common.theming.ExtraLargeSpacing
import com.nrup.mykmmapp.android.common.theming.LargeSpacing
import com.nrup.mykmmapp.android.common.theming.MediumSpacing
import com.nrup.mykmmapp.android.common.theming.SmallSpacing
import com.nrup.mykmmapp.android.R
import com.nrup.mykmmapp.android.common.components.CustomTextField
import com.nrup.mykmmapp.android.common.theming.MyAppTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpUiState,
    onFullNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    onSignUpClick: () -> Unit
) {

    // Getting context here
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    color = if (isSystemInDarkTheme()) {
                        MaterialTheme.colorScheme.background
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                )
                .padding(
                    top = ExtraLargeSpacing + LargeSpacing,
                    start = ExtraLargeSpacing + MediumSpacing,
                    end = ExtraLargeSpacing + MediumSpacing,
                    bottom = LargeSpacing
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Absolute.spacedBy(LargeSpacing)
        ) {
            CustomTextField(
                value = uiState.fullName,
                onValueChange = onFullNameChange,
                hint = R.string.full_name_hint
            )

            CustomTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                hint = R.string.email_hint,
                keyboardType = KeyboardType.Email
            )

            CustomTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                hint = R.string.password_hint,
                keyboardType = KeyboardType.Password,
                isPasswordTextField = true
            )

            ElevatedButton(
                onClick = {
                    onSignUpClick()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(ButtonHeight),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = stringResource(id = R.string.signup_button_hint))
            }

            GoToLogin(modifier) {
                onNavigateToLogin()
            }
        }

        // ============ SHOWING LOADER UNTIL AUTHENTICATION GET DONE

        if (uiState.isAuthenticating) {
            CircularProgressIndicator()
        }
    }

    /*
        LAUNCH EFFECT
        check user authentication success then go to Home else show error message
    */

    LaunchedEffect(
        key1 = uiState.authenticationSucceed,
        key2 = uiState.authErrorMessage,

        block = {
            if (uiState.authenticationSucceed) {
                onNavigateToHome()
            }

            if (uiState.authErrorMessage != null) {
                Toast.makeText(context, uiState.authErrorMessage, Toast.LENGTH_SHORT).show()
            }
        })

}

@Composable
fun GoToLogin(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit
) {
    Row(
        modifier = modifier, horizontalArrangement = Arrangement.spacedBy(
            SmallSpacing
        )
    ) {
        Text(text = "Have already an account?", style = MaterialTheme.typography.labelSmall)
        Text(
            text = "Login",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.clickable { onNavigateToLogin() }
        )
    }
}


@Preview
@Composable
fun SignUpScreenPreview() {
    MyAppTheme {
        SignUpScreen(
            uiState = SignUpUiState(),
            onFullNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onNavigateToLogin = {},
            onNavigateToHome = {},
            onSignUpClick = {}
        )
    }
}
