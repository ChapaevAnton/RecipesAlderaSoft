package com.w4eret1ckrtb1tch.recipesalderasoft.domain.repository

import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import io.reactivex.Single
import java.util.*

interface RecipesRepository {

    fun getRecipes(): Single<List<RecipeEntity>?>

    fun getRecipe(uuidRecipe: UUID): Single<RecipeEntity>
}