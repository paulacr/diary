package com.paulacr.dreamdiary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DreamDiaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}