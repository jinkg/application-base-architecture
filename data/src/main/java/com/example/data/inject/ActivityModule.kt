package com.example.data.inject

import android.app.Activity
import android.content.Context
import com.example.data.inject.scope.ActivityScope
import com.example.data.inject.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Yalin on 2018/11/29
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun getContext(): Activity {
        return activity
    }
}
