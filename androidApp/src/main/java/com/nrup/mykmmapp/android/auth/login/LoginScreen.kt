package com.nrup.mykmmapp.android.auth.login

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

// https://www.youtube.com/watch?v=gIBbsK9SIhA&list=PL2OhfKAEqtl99uxJMCKFM7XbcRmEQVyhW&index=2

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    onSignInClick: () -> Unit,
    onNavigateToSignUp: () -> Unit,
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
                    onSignInClick()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(ButtonHeight),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = stringResource(id = R.string.login_button_hint))
            }

            GoToSignup(modifier) {
                onNavigateToSignUp()
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

@Preview
@Composable
fun LoginScreenPreview() {
    MyAppTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onNavigateToHome = {},
            onSignInClick = {},
            onNavigateToSignUp = {}
        )
    }
}

@Composable
fun GoToSignup(
    modifier: Modifier = Modifier,
    onNavigateToSignup: () -> Unit
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(SmallSpacing)) {
        Text(text = "Don't have an account?", style = MaterialTheme.typography.labelMedium)
        Text(text = "SignUp",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.clickable { onNavigateToSignup() })
    }
}