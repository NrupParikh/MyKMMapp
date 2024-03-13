package com.nrup.mykmmapp.android.di

import com.nrup.mykmmapp.android.auth.login.LoginViewModel
import com.nrup.mykmmapp.android.auth.signup.SignUpViewModel
import com.nrup.mykmmapp.android.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { HomeScreenViewModel() }
}