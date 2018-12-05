package com.example.jinyalin.arch.inject

import com.example.data.ArchRepository
import com.example.data.inject.scope.ApplicationScope
import com.example.jinyalin.arch.ViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Yalin on 2018/12/5
 */
@Module
class ViewModelModule {
    @Provides
    @ApplicationScope
    fun provideViewModelFactory(archRepository: ArchRepository)
            : ViewModelFactory {
        return ViewModelFactory(archRepository)
    }
}