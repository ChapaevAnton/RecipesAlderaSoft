package com.w4eret1ckrtb1tch.recipesalderasoft.di

import com.w4eret1ckrtb1tch.recipesalderasoft.di.viewmodel.ViewModelFactory
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelFactory::class
        ]
    )
    fun mainActivity(): MainActivity

}