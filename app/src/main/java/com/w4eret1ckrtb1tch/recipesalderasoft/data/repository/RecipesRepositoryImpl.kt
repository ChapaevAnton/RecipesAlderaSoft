package com.w4eret1ckrtb1tch.recipesalderasoft.data.repository

import com.w4eret1ckrtb1tch.recipesalderasoft.data.datasource.AlderaSoftApi
import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipeDto
import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipeResponse
import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipesResponse
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.mapper.RecipeMapper
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.mapper.RecipesMapper
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.repository.RecipesRepository
import io.reactivex.Single
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val api: AlderaSoftApi,
    private val mapperRecipes: @JvmSuppressWildcards RecipesMapper<RecipeDto, RecipesResponse>,
    private val mapperRecipe: @JvmSuppressWildcards RecipeMapper<RecipeResponse>
) : RecipesRepository {

    override fun getRecipes(): Single<List<RecipeEntity>?> {
        return api.getRecipes().map { mapperRecipes.mapResponseToEntity(it) }
    }

    override fun getRecipe(uuidRecipe: String?): Single<RecipeEntity?> {
        return api.getRecipe(uuidRecipe).map { mapperRecipe.mapRecipeDtoToEntity(it) }
    }
}