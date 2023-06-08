package com.lizarragabriel.mynode.di

import com.lizarragabriel.mynode.api.JsonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RetrofitModule {
    // http://192.168.102.2
    private const val URL = "http://192.168.102.2:9000/"

    @Singleton
    @Provides
    fun mProvidesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun mProvidesApi(retrofit: Retrofit): JsonApi {
        return retrofit.create(JsonApi::class.java)
    }
}