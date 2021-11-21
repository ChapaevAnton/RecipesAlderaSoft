package com.w4eret1ckrtb1tch.recipesalderasoft.di

import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.recipesalderasoft.di.viewmodel.ViewModelFactory
import com.w4eret1ckrtb1tch.recipesalderasoft.di.viewmodel.ViewModelKey
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.viewmodel.DescriptionRecipeViewModel
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.viewmodel.ListRecipesViewModel
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.fragment.DescriptionRecipeFragment
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.fragment.ListRecipesFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelFactory::class])
    fun listRecipesFragment(): ListRecipesFragment

    @ContributesAndroidInjector(modules = [ViewModelFactory::class])
    fun descriptionRecipeFragment(): DescriptionRecipeFragment

    @Binds
    @[IntoMap ViewModelKey(ListRecipesViewModel::class)]
    fun bindsListRecipesViewModel(listRecipesViewModel: ListRecipesViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(DescriptionRecipeViewModel::class)]
    fun bindsDescriptionRecipeViewModel(descriptionRecipeViewModel: DescriptionRecipeViewModel): ViewModel
}