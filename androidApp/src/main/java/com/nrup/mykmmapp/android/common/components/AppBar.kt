@file:Suppress("UNUSED_EXPRESSION")

package com.nrup.mykmmapp.android.common.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.nrup.mykmmapp.android.R
import com.nrup.mykmmapp.android.destinations.HomeDestination
import com.nrup.mykmmapp.android.destinations.LoginDestination
import com.nrup.mykmmapp.android.destinations.SignUPDestination
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val currentDestination = navHostController.currentDestinationAsState().value

    Surface(
        modifier = modifier,
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = getAppBarTitle(currentDestination?.route)))
            },
            modifier = modifier,
            actions = {
                androidx.compose.animation.AnimatedVisibility(visible = currentDestination?.route == HomeDestination.route) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.logout),
                            contentDescription = null
                        )
                    }
                }
            },
            navigationIcon = {
                if (shouldShowNavigationIcon(currentDestination?.route)) {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_back),
                            contentDescription = null
                        )
                    }
                } else {
                    null
                }
            }
        )
    }

}

private fun getAppBarTitle(currentDestinationRoute: String?): Int {
    return when (currentDestinationRoute) {
        LoginDestination.route -> R.string.login_destination_title
        SignUPDestination.route -> R.string.signup_destination_title
        HomeDestination.route -> R.string.home_destination_title
        else -> R.string.no_destination_title
    }
}

private fun shouldShowNavigationIcon(currentDestinationRoute: String?): Boolean {
    return !(currentDestinationRoute == LoginDestination.route
            || currentDestinationRoute == SignUPDestination.route
            || currentDestinationRoute == HomeDestination.route
            || currentDestinationRoute == null
            )
}