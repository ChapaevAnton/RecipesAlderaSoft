package com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class SimilarEntity(
    val uuid: UUID,
    val name: String?,
    val image: String?
) : Parcelable
