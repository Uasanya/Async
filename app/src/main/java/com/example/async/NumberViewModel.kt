package com.example.async

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NumberViewModel(private val repository: Repository) : ViewModel() {

    private val _randomNumberFlow = MutableStateFlow(0)
    val randomNumberFlow: StateFlow<Int> get() = _randomNumberFlow

    fun getRandomNumbers(){
        viewModelScope.launch{
            repository.getRandomFlow().collect{ randomNumber ->
                _randomNumberFlow.value = randomNumber
            }
        }
    }

    companion object {

        fun provideFactory(
            repository: Repository,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return NumberViewModel(repository) as T
                }
            }
    }
}