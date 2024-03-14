package com.nrup.mykmmapp.android.common.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.nrup.mykmmapp.android.MainActivityViewModel
import org.koin.androidx.compose.koinViewModel


// Logout dialog with OK and Cancel

@Composable
fun ShowLogoutDialog(showDialog: MutableState<Boolean>) {
    val viewModel: MainActivityViewModel = koinViewModel()
    AlertDialog(
        onDismissRequest = {
            //showDialog.value = false
        },
        title = {
            Text(text = "My KMM app")
        },
        text = {
            Text("Are you sure you want to logout?")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.logout()
                    showDialog.value = false
                }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    showDialog.value = false
                }) {
                Text("Cancel")
            }
        }
    )
}