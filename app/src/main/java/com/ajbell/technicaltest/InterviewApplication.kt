package com.ajbell.technicaltest

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class InterviewApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        context = applicationContext
        super.onCreate()
    }
}