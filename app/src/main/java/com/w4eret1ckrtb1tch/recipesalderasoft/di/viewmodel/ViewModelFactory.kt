package com.w4eret1ckrtb1tch.recipesalderasoft.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.factories.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface ViewModelFactory {

    @Reusable
    @Binds
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}