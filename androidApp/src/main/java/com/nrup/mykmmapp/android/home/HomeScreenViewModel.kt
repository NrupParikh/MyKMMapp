package com.nrup.mykmmapp.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nrup.mykmmapp.android.common.datastore.UserSettings
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val dataStore: DataStore<UserSettings>
) : ViewModel() {

    private val _userSettings = MutableStateFlow<UserSettings?>(UserSettings())
    private val userSettings: StateFlow<UserSettings?> = _userSettings.asStateFlow()

    // Getting user details (Flow) from dataStore and store in the StateFlow
    init {
        viewModelScope.launch {
            dataStore.data.collect { settings ->
                _userSettings.value = settings
            }
        }
    }

    var uiState by mutableStateOf(HomeUiState())
        private set

    // Assign userSettings object to uiState for update the data on the UI

    private val userDetails: UserSettings? = userSettings.value

    fun fetchUserData() {

        viewModelScope.launch {

            // Fake delay, as we are getting data from the DataStore but show loading
            delay(1000)

            uiState = uiState.copy(
                isLoading = false,
                userSettings = userDetails ?: UserSettings()
            )

        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = true,
    val userSettings: UserSettings = UserSettings()
)