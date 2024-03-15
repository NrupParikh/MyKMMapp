package com.nrup.mykmmapp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nrup.mykmmapp.android.common.components.AppBar
import com.nrup.mykmmapp.android.destinations.HomeDestination
import com.nrup.mykmmapp.android.destinations.LoginDestination

import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun MyKmmAppNavigation(
    token: String?
) {
    val navHostController = rememberNavController()
//    val scaffoldState = rememberScaffoldState()
    val systemUiController = rememberSystemUiController()

    val isSystemInDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemInDark) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
    }

    // A side-effect is a change to the state of the app that happens outside the scope of a composable function
    // Here we change the status bar color based on system theme outside the scope of a composable function
    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = !isSystemInDark
        )
    }

    Scaffold(
//        scaffoldState = scaffoldState,
        topBar = {
            AppBar(modifier = Modifier, navHostController = navHostController)
        }
    ) { innerPaddings ->
        DestinationsNavHost(
            modifier = Modifier.padding(innerPaddings),
            navGraph = NavGraphs.root,
            navController = navHostController
        )
    }

    // Launches a coroutine in the background to perform your desired side effect.
    // Observe the token value.
    // When user click on logout, it observe the change and navigate to LoginScreen
    LaunchedEffect(key1 = token, block = {

        if (token != null && token.isEmpty()) {
            navHostController.navigate(LoginDestination.route) {
                popUpTo(HomeDestination.route) {
                    inclusive = true
                }
            }
        }
    })
}