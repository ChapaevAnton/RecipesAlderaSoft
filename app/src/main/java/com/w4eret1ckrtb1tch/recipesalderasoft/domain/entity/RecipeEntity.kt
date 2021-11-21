package com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeEntity(
    val uuid: String,
    val name: String?,
    val images: List<String>?,
    val lastUpdated: Long?,
    val description: String?,
    val instructions: String?,
    val difficulty: Int?,
    val similar: List<SimilarEntity>?
) : Parcelable