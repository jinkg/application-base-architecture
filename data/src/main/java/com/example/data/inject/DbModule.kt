package com.example.data.inject

import android.content.Context
import com.example.data.database.ArchDatabase
import com.example.data.inject.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Yalin on 2018/11/29
 */
@Module(includes = [ContextModule::class])
class DbModule {

    @Provides
    @ApplicationScope
    fun getDatabase(context: Context): ArchDatabase {
        return ArchDatabase.getInstance(context)
    }
}
