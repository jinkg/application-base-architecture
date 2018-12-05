package com.example.jinyalin.arch.inject.activity

import com.example.data.inject.scope.ActivityScope
import com.example.jinyalin.arch.MainActivity
import com.example.jinyalin.arch.inject.AppComponent
import dagger.Component

/**
 * Yalin on 2018/12/5
 */
@Component(
    modules = [GlideModule::class, MainActivityModule::class],
    dependencies = [AppComponent::class]
)
@ActivityScope
interface ActivityComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}