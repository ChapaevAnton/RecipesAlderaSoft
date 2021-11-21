package com.w4eret1ckrtb1tch.recipesalderasoft.domain.mapper

import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity

interface RecipesMapper<in ITEM_DTO, in RESPONSE> {

    fun mapRecipeDtoToEntity(recipe: ITEM_DTO): RecipeEntity

    fun mapResponseToEntity(recipesResponse: RESPONSE): List<RecipeEntity>?
}