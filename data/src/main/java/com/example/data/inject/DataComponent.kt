package com.example.data.inject

import com.example.data.ArchRepository
import com.example.data.inject.scope.ApplicationScope
import dagger.Component

/**
 * Yalin on 2018/11/29
 */
@ApplicationScope
@Component(modules = [RetrofitModule::class, DbModule::class])
interface DataComponent {
    fun getArchRepository(): ArchRepository
}

