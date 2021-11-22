package com.w4eret1ckrtb1tch.recipesalderasoft.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.Result
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.usecase.GetRecipesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListRecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val recipes: MutableLiveData<Result<List<RecipeEntity>>> = MutableLiveData()
    val getRecipes: LiveData<Result<List<RecipeEntity>>> = recipes

    init {
        loadRecipes()
    }

    @SuppressLint("CheckResult")
    fun loadRecipes() {
        recipes.value = Result.Loading
        getRecipesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listRecipe ->
                Log.d("TAG", "recipes load: $listRecipe")
                recipes.value = listRecipe?.let { Result.Success(it) }
            }, { error ->
                Log.d("TAG", "recipes load: $error")
                recipes.value = Result.Failure(error)
            })
    }
}