package com.lizarragabriel.mynode.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizarragabriel.mynode.R
import com.lizarragabriel.mynode.api.City
import com.lizarragabriel.mynode.repository.Repository
import com.lizarragabriel.mynode.utils.MyEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var job: Job? = null

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var _navigate = MutableLiveData<MyEvent<Int>>()
    val navigate: LiveData<MyEvent<Int>> get() = _navigate

    fun mAddButton(mName: String, mDescription: String, mUrl: String) {
        if(mName.isEmpty() || mDescription.isEmpty() || mUrl.isEmpty()) {
            return
        }
        mAdd(mName, mDescription, mUrl)
    }

    private fun mAdd(name: String, description: String, url: String) {
        _loading.postValue(true)
        val pilar = City(null, name, url, description)
        job = viewModelScope.launch {
            try {
                val response = repository.addCity(pilar)
                if(response.isSuccessful) {
                    mNavigateHome()
                }
                _loading.postValue(false)
            } catch (e: Exception) {
                _loading.postValue(false)
            }
        }
    }

    private fun mNavigateHome() {
        _navigate.value = MyEvent(R.id.action_addFragment_to_homeFragment)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}