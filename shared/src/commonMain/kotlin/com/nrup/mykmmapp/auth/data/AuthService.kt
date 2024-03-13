package com.nrup.mykmmapp.auth.data

import com.nrup.mykmmapp.common.data.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class AuthService : KtorApi() {

    // http://10.37.54.125:8080/auth/register
    suspend fun signUp(request: SignUpRequest): AuthResponse = client.post {
        endPoint(path = "auth/register")
        setBody(request)
    }.body()

    // http://10.37.54.125:8080/auth/login
    suspend fun signIn(request: SignInRequest): AuthResponse = client.post {
        endPoint(path = "auth/login")
        setBody(request)
    }.body()
}