package com.example.jinyalin.arch.inject

import com.example.data.inject.DataComponent
import com.example.data.inject.scope.ApplicationScope
import com.example.jinyalin.arch.MainActivity
import dagger.Component

/**
 * Yalin on 2018/11/29
 */
@ApplicationScope
@Component(dependencies = [DataComponent::class])
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}

