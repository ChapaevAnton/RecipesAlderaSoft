package com.w4eret1ckrtb1tch.recipesalderasoft.data.dto

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @field:SerializedName("uuid") val uuid: String?,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("images") val images: List<String>?,
    @field:SerializedName("lastUpdated") val lastUpdated: Long?,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("instructions") val instructions: String?,
    @field:SerializedName("difficulty") val difficulty: Int?,
    @field:SerializedName("similar") val similar: List<SimilarDto>?
)
