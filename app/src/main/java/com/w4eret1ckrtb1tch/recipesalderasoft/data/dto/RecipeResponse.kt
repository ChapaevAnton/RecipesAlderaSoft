package com.w4eret1ckrtb1tch.recipesalderasoft.data.dto

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @field:SerializedName("recipe") val recipe: RecipeDto?
)