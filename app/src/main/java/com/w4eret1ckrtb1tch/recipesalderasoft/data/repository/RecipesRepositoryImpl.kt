package com.w4eret1ckrtb1tch.recipesalderasoft.data.repository

import com.w4eret1ckrtb1tch.recipesalderasoft.data.datasource.AlderaSoftApi
import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipeDto
import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipesResponse
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.mapper.RecipesMapper
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.repository.RecipesRepository
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val api: AlderaSoftApi,
    private val mapper: @JvmSuppressWildcards RecipesMapper<RecipeDto, RecipesResponse>
) : RecipesRepository {

    override fun getRecipes(): Single<List<RecipeEntity>?> {
        return api.getRecipes().map { mapper.mapResponseToEntity(it) }
    }

    override fun getRecipe(uuidRecipe: UUID): Single<RecipeEntity> {
        TODO("Not yet implemented")
    }
}