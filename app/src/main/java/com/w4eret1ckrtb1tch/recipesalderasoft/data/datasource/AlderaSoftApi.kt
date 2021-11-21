package com.w4eret1ckrtb1tch.recipesalderasoft.data.datasource

import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipeResponse
import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AlderaSoftApi {

    @GET("recipes")
    fun getRecipes(): Single<RecipesResponse>

    @GET("recipes/{uuidRecipe}")
    fun getRecipe(@Path("uuidRecipe") uuidRecipe: String?): Single<RecipeResponse>

    companion object Options {
        const val BASE_URL = "https://test.kode-t.ru/"
    }
}