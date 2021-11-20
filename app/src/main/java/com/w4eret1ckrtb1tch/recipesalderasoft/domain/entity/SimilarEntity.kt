package com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimilarEntity(
    val uuid: String,
    val name: String?,
    val image: String?
) : Parcelable
