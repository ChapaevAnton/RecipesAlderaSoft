package com.w4eret1ckrtb1tch.recipesalderasoft.data.dto

import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @field:SerializedName("recipes") val recipes: List<RecipeDto>?
)