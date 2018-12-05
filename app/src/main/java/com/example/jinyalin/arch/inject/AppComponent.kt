package com.example.jinyalin.arch.inject

import com.example.data.inject.DataComponent
import com.example.data.inject.scope.ApplicationScope
import com.example.jinyalin.arch.MainActivity
import com.example.jinyalin.arch.ViewModelFactory
import dagger.Component

/**
 * Yalin on 2018/11/29
 */
@ApplicationScope
@Component(dependencies = [DataComponent::class], modules = [ViewModelModule::class])
interface AppComponent {
    fun getViewModelFactory(): ViewModelFactory
}

