package com.example.data.inject

import com.example.data.ArchRepository
import com.example.data.database.ArchDatabase
import com.example.data.inject.scope.ApplicationScope
import com.example.data.network.NetworkApi
import dagger.Module
import dagger.Provides

/**
 * Yalin on 2018/12/5
 */
@Module
class RepoModule {
    @Provides
    @ApplicationScope
    fun provideArchRepository(networkApi: NetworkApi, archDatabase: ArchDatabase)
            : ArchRepository {
        return ArchRepository(networkApi, archDatabase)
    }
}