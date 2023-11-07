package com.example.async

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NumberViewModel(private val repository: Repository) : ViewModel() {

    private val _randomNumberLiveData = MutableLiveData<Int>()
    val randomNumberLiveData: LiveData<Int>
        get() = _randomNumberLiveData

    fun getRandomNumbers(){
        viewModelScope.launch{
            repository.getRandomFlow().collect{ randomNumber ->
                _randomNumberLiveData.value = randomNumber
            }
        }
    }
}