package com.example.lovelyanimals

import android.app.Application

class AndroidApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .build()

        applicationComponent.inject(this)
    }
}