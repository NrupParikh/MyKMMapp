package com.nrup.mykmmapp.auth.domain.repository

import com.nrup.mykmmapp.auth.data.AuthService
import com.nrup.mykmmapp.auth.data.SignInRequest
import com.nrup.mykmmapp.auth.data.SignUpRequest
import com.nrup.mykmmapp.auth.domain.model.AuthResultData
import com.nrup.mykmmapp.common.util.DispatcherProvider
import com.nrup.mykmmapp.common.util.Result
import kotlinx.coroutines.withContext
import toAuthResultData

internal class AuthRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val authService: AuthService
) : AuthRepository {
    override suspend fun signUp(
        fullName: String,
        email: String,
        password: String
    ): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {
                val request = SignUpRequest(fullName, email, password)
                val authResponse = authService.signUp(request)
                if (authResponse.data == null) {
                    Result.Error(
                        message = authResponse.errorMessage!!
                    )
                } else {
                    Result.Success(data = authResponse.data.toAuthResultData())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Result.Error(message = "We couldn't send your request, try later")
            }
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {
                val request = SignInRequest(email, password)
                val authResponse = authService.signIn(request)
                if (authResponse.data == null) {
                    Result.Error(
                        message = authResponse.errorMessage!!
                    )
                } else {
                    Result.Success(data = authResponse.data.toAuthResultData())
                }
            } catch (e: Exception) {
                Result.Error(message = "We couldn't send your request, try later")
            }
        }
    }
}