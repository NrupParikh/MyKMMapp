package com.nrup.mykmmapp.android.common.datastore

import com.nrup.mykmmapp.auth.domain.model.AuthResultData
import kotlinx.serialization.Serializable

// Storing AuthResultData to UserSettings in shared preference / ProtoDataStore
@Serializable
data class UserSettings(
    val id: Int = -1,
    val fullName: String = "",
    val email: String = "",
    var authToken: String = "",
    val createdAt: String = ""
)

// Creating mapper function to convert UserSettings to AuthResultData
// GET
fun UserSettings.toAuthResultData(): AuthResultData {
    return AuthResultData(id, fullName, email, authToken, createdAt)
}

// Creating mapper function to convert AuthResultData to UserSettings
// SET
fun AuthResultData.toUserSettings(): UserSettings {
    return UserSettings(id, fullName, email, authToken, createdAt)
}