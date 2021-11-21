package com.w4eret1ckrtb1tch.recipesalderasoft.data.datasource

import com.w4eret1ckrtb1tch.recipesalderasoft.data.dto.RecipesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AlderaSoftApi {

    @GET("recipes")
    fun getRecipes(): Single<RecipesResponse>

    companion object Options {
        const val BASE_URL = "https://test.kode-t.ru/"
    }
}