package com.example.async

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class NumberViewModel : ViewModel() {

    private val _randomNumberLiveData = MutableLiveData<List<Int>>()
    val randomNumberLiveData: LiveData<List<Int>>
        get() = _randomNumberLiveData
    private var list = listOf<Int>()
    private val thread = object : Thread() {
        override fun run() {
            while (true) {
                if (this.isInterrupted) return
                try {
                    val randomNumber = Random.nextInt(0, 100)
                    list = list + randomNumber
                    _randomNumberLiveData.postValue(list)
                    sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    init {
        thread.start()
    }

    override fun onCleared() {
        super.onCleared()
        thread.interrupt()
    }
}