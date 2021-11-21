package com.w4eret1ckrtb1tch.recipesalderasoft.data.mapper

import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipeResponse
import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.SimilarDto
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.SimilarEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.mapper.RecipeMapper
import java.util.*
import javax.inject.Inject

class RecipeMapperResponseImpl @Inject constructor() :
    RecipeMapper<@JvmSuppressWildcards RecipeResponse> {

    override fun mapRecipeDtoToEntity(recipeResponse: RecipeResponse): RecipeEntity? {
        return recipeResponse.recipe?.let { recipe ->
            RecipeEntity(
                uuid = recipe.uuid ?: UUID.randomUUID().toString(),
                name = recipe.name,
                images = recipe.images,
                lastUpdated = recipe.lastUpdated,
                description = recipe.description,
                instructions = recipe.instructions,
                difficulty = recipe.difficulty,
                similar = mapSimilarDtoToEntity(recipe.similar)
            )
        }
    }

    private fun mapSimilarDtoToEntity(similar: List<SimilarDto>?): List<SimilarEntity>? {
        return similar?.map {
            SimilarEntity(
                uuid = UUID.fromString(it.uuid),
                name = it.name,
                image = it.image
            )
        }
    }
}

