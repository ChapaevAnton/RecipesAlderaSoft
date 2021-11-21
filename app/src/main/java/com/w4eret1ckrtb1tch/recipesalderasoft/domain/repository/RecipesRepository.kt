package com.w4eret1ckrtb1tch.recipesalderasoft.domain.repository

import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import io.reactivex.Single

interface RecipesRepository {

    fun getRecipes(): Single<List<RecipeEntity>?>

    fun getRecipe(uuidRecipe: String?): Single<RecipeEntity?>
}