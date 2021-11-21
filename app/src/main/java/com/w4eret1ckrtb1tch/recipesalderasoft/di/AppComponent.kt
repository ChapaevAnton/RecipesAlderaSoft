package com.w4eret1ckrtb1tch.recipesalderasoft.di

import android.content.Context
import com.w4eret1ckrtb1tch.recipesalderasoft.App
import com.w4eret1ckrtb1tch.recipesalderasoft.di.viewmodel.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        DomainModule::class,
        NetworkModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelFactory::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}