package com.nrup.mykmmapp.auth.domain.usecase

import com.nrup.mykmmapp.auth.domain.model.AuthResultData
import com.nrup.mykmmapp.auth.domain.repository.AuthRepository
import com.nrup.mykmmapp.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// Koin used to inject repository here
// Here we use this USE CASE  for check validations

class SignInUseCase : KoinComponent {
    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<AuthResultData> {

        if (email.isBlank()) {
            return Result.Error(
                message = "Please enter email"
            )
        } else if ("@" !in email) {
            return Result.Error(
                message = "Please enter valid email"
            )
        }
        if (password.isBlank()) {
            return Result.Error(
                message = "Please enter password"
            )

        } else if (password.length < 6) {
            return Result.Error(
                message = "Please must be more than 6 letter"
            )
        }
        return repository.signIn(email, password)
    }
}