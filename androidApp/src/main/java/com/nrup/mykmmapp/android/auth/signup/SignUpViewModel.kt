package com.nrup.mykmmapp.android.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nrup.mykmmapp.android.common.datastore.UserSettings
import com.nrup.mykmmapp.android.common.datastore.toUserSettings
import com.nrup.mykmmapp.auth.domain.usecase.SignUpUseCase
import com.nrup.mykmmapp.common.util.Result
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val dataStore: DataStore<UserSettings>
) : ViewModel() {
    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun signUp() {
        viewModelScope.launch {
            uiState = uiState.copy(isAuthenticating = true)

            val authResultData = signUpUseCase(
                uiState.fullName,
                uiState.email,
                uiState.password
            )

            uiState = when (authResultData) {
                is Result.Error -> {
                    uiState.copy(
                        isAuthenticating = false,
                        authErrorMessage = authResultData.message
                    )
                }

                is Result.Success -> {

                    // When Login success, we store API response to shared preference
                    dataStore.updateData {
                        authResultData.data!!.toUserSettings()
                    }

                    // Updating the flags
                    uiState.copy(
                        isAuthenticating = false,
                        authenticationSucceed = true
                    )
                }
            }
        }

    }

    fun updateFullName(input: String) {
        uiState = uiState.copy(fullName = input)
    }

    fun updateEmail(input: String) {
        uiState = uiState.copy(email = input)
    }

    fun updatePassword(input: String) {
        uiState = uiState.copy(password = input)
    }

}


data class SignUpUiState(
    var fullName: String = "",
    var email: String = "",
    var password: String = "",
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false
)