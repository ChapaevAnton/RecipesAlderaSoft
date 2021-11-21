package com.w4eret1ckrtb1tch.recipesalderasoft

import com.w4eret1ckrtb1tch.recipesalderasoft.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().context(applicationContext).build()
    }
}