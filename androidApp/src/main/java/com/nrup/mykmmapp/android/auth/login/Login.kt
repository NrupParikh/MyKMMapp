package com.nrup.mykmmapp.android.auth.login

import androidx.compose.runtime.Composable
import com.nrup.mykmmapp.android.destinations.HomeScreenDestination
import com.nrup.mykmmapp.android.destinations.LoginDestination
import com.nrup.mykmmapp.android.destinations.SignUPDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun Login(
    navigator: DestinationsNavigator
) {
    val viewModel: LoginViewModel = koinViewModel()
    LoginScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onSignInClick   = viewModel::signIn,
        onNavigateToHome = {
            navigator.navigate(HomeScreenDestination) {
                popUpTo(LoginDestination.route) {
                    inclusive = true
                }
            }
        },
        onNavigateToSignUp =
        {
            navigator.navigate(SignUPDestination) {
                popUpTo(LoginDestination.route) {
                    inclusive = true
                }
            }
        }
    )
}