package com.w4eret1ckrtb1tch.recipesalderasoft.domain.mapper

import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity

interface RecipeMapper<in RESPONSE> {

    fun mapRecipeDtoToEntity(recipeResponse: RESPONSE): RecipeEntity?

}