package com.lizarragabriel.mynode.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizarragabriel.mynode.api.City
import com.lizarragabriel.mynode.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    val employeeList: LiveData<List<City>> get() = _employeeList
    private val _employeeList: MutableLiveData<List<City>> by lazy {
        MutableLiveData<List<City>>().also {
            viewModelScope.launch {
                it.postValue(mloadEmployees())
            }
        }
    }

    suspend fun mloadEmployees(): List<City> {
        return try {
            val response = repository.loadCities()
            if(response.isSuccessful) {
                response.body()!!
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun mReload() {
        try {
            val response = repository.loadCities()
            if(response.isSuccessful) {

                _employeeList.postValue(response.body())
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }

    suspend fun mDelete(id: String) {
        val response = repository.deleteCity(id)
    }
}