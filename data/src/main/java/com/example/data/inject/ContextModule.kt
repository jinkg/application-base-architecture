package com.example.data.inject

import android.content.Context
import com.example.data.inject.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Yalin on 2018/11/29
 */
@Module
class ContextModule(private val context: Context) {

    @Provides
    @ApplicationScope
    fun getContext(): Context {
        return context.applicationContext
    }
}
