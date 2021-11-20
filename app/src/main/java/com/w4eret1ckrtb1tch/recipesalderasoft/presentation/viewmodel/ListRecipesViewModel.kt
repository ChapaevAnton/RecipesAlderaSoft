package com.w4eret1ckrtb1tch.recipesalderasoft.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.usecase.GetRecipesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListRecipesViewModel constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val recipes: MutableLiveData<List<RecipeEntity>> = MutableLiveData()
    val getRecipes: LiveData<List<RecipeEntity>> = recipes

    init {
        loadRecipes()
    }

    // OPTIMIZE: 20.11.2021 refactoring to result
    @SuppressLint("CheckResult")
    private fun loadRecipes() {
        getRecipesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG", "recipes load: ok")
                recipes.value = it
            }, { error ->
                Log.d("TAG", "recipes load: $error")
            })
    }
}