package com.kky.cleanarchitecturesample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication: Application() {

    companion object {
        const val TAG = "CleanArchitectureSample"
    }
}