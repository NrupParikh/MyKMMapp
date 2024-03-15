package com.nrup.mykmmapp.android.auth.signup

import androidx.compose.runtime.Composable
import com.nrup.mykmmapp.android.destinations.HomeDestination
import com.nrup.mykmmapp.android.destinations.LoginDestination
import com.nrup.mykmmapp.android.destinations.SignUPDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun SignUP(
    navigator: DestinationsNavigator
) {
    val viewModel: SignUpViewModel = koinViewModel()

    SignUpScreen(
        uiState = viewModel.uiState,
        onFullNameChange = viewModel::updateFullName,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onNavigateToLogin = {
            navigator.navigate(LoginDestination) {
                popUpTo(SignUPDestination.route) {
                    inclusive = true
                }
            }
        },
        onNavigateToHome = {
            navigator.navigate(HomeDestination) {
                popUpTo(SignUPDestination.route) {
                    inclusive = true
                }
            }
        },
        onSignUpClick = viewModel::signUp
    )
}