package com.example.jinyalin.arch.inject

import android.content.Context
import com.example.jinyalin.arch.inject.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Yalin on 2018/11/29
 */
@Module
class ContextModule(private val context: Context) {

    @Provides
    @ApplicationScope
    internal fun getContext(): Context {
        return context.applicationContext
    }
}
