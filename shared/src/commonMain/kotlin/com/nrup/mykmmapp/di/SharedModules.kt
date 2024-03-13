package com.nrup.mykmmapp.di

import com.nrup.mykmmapp.auth.data.AuthService
import com.nrup.mykmmapp.auth.domain.repository.AuthRepository
import com.nrup.mykmmapp.auth.domain.repository.AuthRepositoryImpl
import com.nrup.mykmmapp.auth.domain.usecase.SignInUseCase
import com.nrup.mykmmapp.auth.domain.usecase.SignUpUseCase
import com.nrup.mykmmapp.common.util.provideDispatcher
import org.koin.dsl.module

// Creating single instance and factory methods

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

// This will called from our Android Application or IOS
fun getSharedModules() = listOf(authModule, utilityModule)