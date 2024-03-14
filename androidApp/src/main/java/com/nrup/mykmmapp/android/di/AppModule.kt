package com.nrup.mykmmapp.android.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.nrup.mykmmapp.android.MainActivityViewModel
import com.nrup.mykmmapp.android.auth.login.LoginViewModel
import com.nrup.mykmmapp.android.auth.signup.SignUpViewModel
import com.nrup.mykmmapp.android.common.datastore.UserSettingsSerializer
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get(),get()) }
    viewModel { SignUpViewModel(get(),get()) }
//    viewModel { HomeScreenViewModel() }
    viewModel { MainActivityViewModel(get()) }

    // Creating Single Instance of our Shared Preference
    single {
        DataStoreFactory.create(
            serializer = UserSettingsSerializer,
            produceFile = {
                androidContext().dataStoreFile(
                    fileName = "my_kmm_app_shared_preference"
                )
            }
        )
    }
}