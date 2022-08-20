package com.example.ktorsample

import android.app.Application
import com.example.ktorsample.module.koinModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
class DIApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DIApplication)

            modules(koinModule)
        }
    }
}