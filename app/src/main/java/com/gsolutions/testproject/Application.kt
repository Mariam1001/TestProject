package com.gsolutions.testproject

import am.md.keepwith.shared.di.module.appModule
import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Application : Application() {

    companion object {
        lateinit var instance: Application
            private set
        fun applicationContext(): Context = instance.applicationContext
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(appModule)
        }
    }
}