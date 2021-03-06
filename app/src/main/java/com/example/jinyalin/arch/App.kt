package com.example.jinyalin.arch

import android.app.Activity
import android.app.Application
import com.example.data.inject.ContextModule
import com.example.data.inject.DaggerDataComponent
import com.example.jinyalin.arch.inject.AppComponent
import com.example.jinyalin.arch.inject.DaggerAppComponent
import timber.log.Timber

/**
 * Yalin on 2018/11/29
 */
class App : Application() {
    private lateinit var appComponent: AppComponent

    companion object {
        fun getApp(activity: Activity): App {
            return activity.application as App
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val dataComponent = DaggerDataComponent.builder().contextModule(ContextModule(this)).build()
        appComponent = DaggerAppComponent.builder().dataComponent(dataComponent).build()
    }

    fun getAppComponent() = appComponent
}
