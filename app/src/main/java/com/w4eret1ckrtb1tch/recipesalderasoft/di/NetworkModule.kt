package com.w4eret1ckrtb1tch.recipesalderasoft.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.w4eret1ckrtb1tch.recipesalderasoft.BuildConfig
import com.w4eret1ckrtb1tch.recipesalderasoft.data.datasource.AlderaSoftApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesAlderaSoftApi(retrofit: Retrofit): AlderaSoftApi =
        retrofit.create(AlderaSoftApi::class.java)

    @Singleton
    @Provides
    fun providesRetrofit(
        builder: Retrofit.Builder,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return builder
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(AlderaSoftApi.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesGsonClient(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BASIC
        }
    }

}