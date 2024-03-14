package com.nrup.mykmmapp.android

import android.app.Application
import com.nrup.mykmmapp.android.di.appModule
import com.nrup.mykmmapp.di.getSharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyKmmApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin for DI
        startKoin {
            // Adding app specific modules and shared module
            modules(appModule + getSharedModules())

            // Required to add Android context which is used by our shared preference in AppModule
            androidContext(this@MyKmmApp)
        }
    }
}