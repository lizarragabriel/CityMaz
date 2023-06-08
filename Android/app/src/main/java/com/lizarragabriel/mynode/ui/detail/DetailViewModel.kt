package com.lizarragabriel.mynode.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    private var _editar = MutableLiveData<Boolean>()
    val editar: LiveData<Boolean> get() = _editar

}