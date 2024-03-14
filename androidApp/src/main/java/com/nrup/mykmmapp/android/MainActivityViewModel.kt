package com.nrup.mykmmapp.android

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nrup.mykmmapp.android.common.datastore.UserSettings
import com.nrup.mykmmapp.android.common.datastore.toAuthResultData
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivityViewModel(private val dataStore: DataStore<UserSettings>) : ViewModel() {

    // Getting token from shared preference
    val authState = dataStore.data.map { it.toAuthResultData().authToken }


    // Logout : clear shared preference data
    fun logout() {
        viewModelScope.launch {
            dataStore.updateData { UserSettings() }
        }
    }

}