package com.example.jinyalin.arch.inject.activity

import com.bumptech.glide.RequestManager
import com.example.data.inject.scope.ActivityScope
import com.example.jinyalin.arch.UserAdapter
import dagger.Module
import dagger.Provides

/**
 * Yalin on 2018/12/5
 */
@Module
class MainActivityModule {
    @Provides
    @ActivityScope
    fun provideUserAdapter(requestManager: RequestManager): UserAdapter {
        return UserAdapter(requestManager)
    }
}