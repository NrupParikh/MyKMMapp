package com.nrup.mykmmapp.auth.domain.repository

import com.nrup.mykmmapp.auth.domain.model.AuthResultData

internal interface AuthRepository {
    suspend fun signUp(
        fullName: String,
        email: String,
        password: String,
    ): com.nrup.mykmmapp.common.util.Result<AuthResultData>

    suspend fun signIn(
        email: String,
        password: String,
    ): com.nrup.mykmmapp.common.util.Result<AuthResultData>
}