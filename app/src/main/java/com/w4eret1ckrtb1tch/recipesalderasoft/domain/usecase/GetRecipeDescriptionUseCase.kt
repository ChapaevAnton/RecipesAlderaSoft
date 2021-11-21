package com.w4eret1ckrtb1tch.recipesalderasoft.domain.usecase

import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.repository.RecipesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetRecipeDescriptionUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    operator fun invoke(uuidRecipe: String?): Single<RecipeEntity?> {
        return repository.getRecipe(uuidRecipe)
    }
}