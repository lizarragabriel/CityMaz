package com.lizarragabriel.mynode.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizarragabriel.mynode.R
import com.lizarragabriel.mynode.api.City
import com.lizarragabriel.mynode.repository.Repository
import com.lizarragabriel.mynode.utils.MyEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var _city = MutableLiveData<City>()
    val city: LiveData<City> get() = _city

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var _navigate = MutableLiveData<MyEvent<Int>>()
    val navigate: LiveData<MyEvent<Int>> get() = _navigate

    fun init(city: City) {
        _city.postValue(city)
    }

    fun mAddButton(mName: String, mDescription: String, mUrl: String) {
        if(mName.isEmpty() || mDescription.isEmpty() || mUrl.isEmpty()) {
            return
        }
        viewModelScope.launch {
            try {
                _loading.postValue(true)
                val emp = City(city.value!!._id, mName, mUrl, mDescription)
                val response = repository.updateCity(emp)
                _loading.postValue(false)
                _navigate.value = MyEvent(R.id.action_editFragment_to_homeFragment)
            } catch (e: Exception) {
                _loading.postValue(false)
            }
        }
    }
}