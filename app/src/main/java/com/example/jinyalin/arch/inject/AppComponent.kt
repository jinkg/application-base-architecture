package com.example.jinyalin.arch.inject

import com.example.jinyalin.arch.MainActivity
import com.example.jinyalin.arch.inject.scope.ApplicationScope
import dagger.Component

/**
 * Yalin on 2018/11/29
 */
@ApplicationScope
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}

