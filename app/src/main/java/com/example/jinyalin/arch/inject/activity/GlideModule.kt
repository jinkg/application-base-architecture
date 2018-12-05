package com.example.jinyalin.arch.inject.activity

import android.app.Activity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.data.inject.ActivityModule
import com.example.data.inject.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Yalin on 2018/12/5
 */
@Module(includes = [ActivityModule::class])
class GlideModule {

    @Provides
    @ActivityScope
    fun getGlide(activity: Activity): RequestManager {
        return Glide.with(activity)
    }
}