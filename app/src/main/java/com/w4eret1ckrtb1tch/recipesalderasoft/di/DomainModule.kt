package com.w4eret1ckrtb1tch.recipesalderasoft.di

import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipeDto
import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipesResponse
import com.w4eret1ckrtb1tch.recipesalderasoft.data.mapper.RecipesMapperResponseImpl
import com.w4eret1ckrtb1tch.recipesalderasoft.data.repository.RecipesRepositoryImpl
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.mapper.RecipesMapper
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.repository.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface DomainModule {

    @Reusable
    @Binds
    fun bindsRecipesMapper(
        recipesMapper: RecipesMapperResponseImpl
    ): @JvmSuppressWildcards RecipesMapper<RecipeDto, RecipesResponse>

    @Reusable
    @Binds
    fun bindsRecipesRepository(recipesRepository: RecipesRepositoryImpl): RecipesRepository

}