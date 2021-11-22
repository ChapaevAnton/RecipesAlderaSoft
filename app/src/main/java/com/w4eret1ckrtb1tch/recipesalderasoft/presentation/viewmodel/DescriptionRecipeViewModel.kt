package com.w4eret1ckrtb1tch.recipesalderasoft.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.Result
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.usecase.GetRecipeDescriptionUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DescriptionRecipeViewModel @Inject constructor(
    private val getRecipeDescriptionUseCase: GetRecipeDescriptionUseCase
) : ViewModel() {

    private val recipeDescription: MutableLiveData<Result<RecipeEntity>> = MutableLiveData()
    val getRecipeDescription: LiveData<Result<RecipeEntity>> = recipeDescription

    @SuppressLint("CheckResult")
    fun loadRecipeDescription(uuidRecipe: String?) {
        recipeDescription.value = Result.Loading
        getRecipeDescriptionUseCase(uuidRecipe)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ recipe ->
                Log.d("TAG", "recipe load: $recipe")
                recipeDescription.value = recipe?.let { Result.Success(it) }
            }, { error ->
                Log.d("TAG", "recipe load: $error")
                recipeDescription.value = Result.Failure(error)
            })
    }

}