package com.technicalTest.technicaltest

import android.app.Application
//import com.technicaltest.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TechnicalTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (true) {
            Timber.plant(Timber.DebugTree())
        }
    }
}