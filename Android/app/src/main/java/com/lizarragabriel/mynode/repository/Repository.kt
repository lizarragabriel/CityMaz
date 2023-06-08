package com.lizarragabriel.mynode.repository

import com.lizarragabriel.mynode.api.City
import com.lizarragabriel.mynode.api.JsonApi
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val mJsonApi: JsonApi) {
    suspend fun loadCities(): Response<List<City>> {
        return mJsonApi.mLoadEmployees()
    }
    suspend fun addCity(employee: City): Response<Void> {
        return mJsonApi.mAddEmployee(employee)
    }
    suspend fun deleteCity(id: String): Response<Void> {
        return mJsonApi.mDeleteEmployee(id)
    }
    suspend fun updateCity(employee: City): Response<Void> {
        return mJsonApi.mUpdate(employee._id!!, employee)
    }
}