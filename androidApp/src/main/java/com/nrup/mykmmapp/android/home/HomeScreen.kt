package com.nrup.mykmmapp.android.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nrup.mykmmapp.android.auth.login.LoginUiState
import com.nrup.mykmmapp.android.common.theming.ExtraLargeSpacing
import com.nrup.mykmmapp.android.common.theming.LargeSpacing
import com.nrup.mykmmapp.android.common.theming.MediumSpacing
import com.nrup.mykmmapp.android.common.theming.MyAppTheme
import com.ramcosta.composedestinations.annotation.Destination

//@Destination(start = true)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    fetchData: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (uiState.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Welcome")
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                verticalArrangement = Arrangement.Absolute.spacedBy(LargeSpacing)
            ) {
                Text(
                    text = "Welcome " + uiState.userSettings.fullName + " !",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Your email is  : " + uiState.userSettings.email,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Created on : " + uiState.userSettings.createdAt,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        fetchData()
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    MyAppTheme {
        HomeScreen(
            uiState = HomeUiState(),
            fetchData = { Unit },
        )
    }
}