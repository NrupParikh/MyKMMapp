package com.nrup.mykmmapp.android

import android.app.Application
import com.nrup.mykmmapp.android.di.appModule
import com.nrup.mykmmapp.di.getSharedModules
import org.koin.core.context.startKoin

class MyKmmApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin for DI : Adding modules

        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}