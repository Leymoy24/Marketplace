package com.example.marketplace

import android.app.Application
import com.example.marketplace.di.component.DaggerAppComponent

class App : Application() {
    val appComponent by lazy { DaggerAppComponent.factory().create(applicationContext) }
}