package com.w4eret1ckrtb1tch.recipesalderasoft.data.mapper

import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipeDto
import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipesResponse
import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.SimilarDto
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.SimilarEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.mapper.RecipesMapper
import java.util.*
import javax.inject.Inject

class RecipesMapperResponseImpl @Inject constructor() :
    RecipesMapper<@JvmSuppressWildcards RecipeDto, @JvmSuppressWildcards RecipesResponse> {

    override fun mapRecipeDtoToEntity(recipe: RecipeDto): RecipeEntity {
        return with(recipe) {
            RecipeEntity(
                uuid = uuid ?: UUID.randomUUID().toString(),
                name = name,
                images = images,
                lastUpdated = lastUpdated,
                description = description,
                instructions = instructions,
                difficulty = difficulty,
                similar = mapSimilarDtoToEntity(similar)
            )
        }
    }

    override fun mapResponseToEntity(recipesResponse: RecipesResponse): List<RecipeEntity>? {
        return recipesResponse.recipes?.map { mapRecipeDtoToEntity(it) }
    }

    private fun mapSimilarDtoToEntity(similar: List<SimilarDto>?): List<SimilarEntity>? {
        return similar?.map {
            SimilarEntity(
                uuid = it.uuid ?: UUID.randomUUID().toString(),
                name = it.name,
                image = it.image
            )
        }
    }
}