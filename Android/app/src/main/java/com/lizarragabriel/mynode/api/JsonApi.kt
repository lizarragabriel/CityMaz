package com.lizarragabriel.mynode.api

import retrofit2.Response
import retrofit2.http.*

interface JsonApi {

    @GET("cities/all")
    suspend fun mLoadEmployees(): Response<List<City>>

    @POST("cities")
    suspend fun mAddEmployee(@Body employee: City): Response<Void>

    @DELETE("cities/{id}")
    suspend fun mDeleteEmployee(@Path("id") id: String): Response<Void>

    @PUT("cities/{id}")
    suspend fun mUpdate(@Path("id") id: String, @Body employee: City): Response<Void>
}