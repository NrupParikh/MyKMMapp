package com.nrup.mykmmapp.android

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import com.nrup.mykmmapp.android.common.datastore.UserSettings
import com.nrup.mykmmapp.android.common.datastore.toAuthResultData
import kotlinx.coroutines.flow.map

class MainActivityViewModel(dataStore: DataStore<UserSettings>) : ViewModel() {

    // Getting token from shared preference
    val authState = dataStore.data.map { it.toAuthResultData().authToken }

}