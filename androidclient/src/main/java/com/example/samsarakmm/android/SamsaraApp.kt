package com.example.samsarakmm.android

import android.app.Application

class SamsaraApp : Application() {
    lateinit var serviceLocator: StorageServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = StorageServiceLocator(this)
    }
}