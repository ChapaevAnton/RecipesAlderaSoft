package com.w4eret1ckrtb1tch.recipesalderasoft.data.dto

import com.google.gson.annotations.SerializedName

data class SimilarDto(
    @field:SerializedName("uuid") val uuid: String?,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("image") val image: String?
)