package com.example.async

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class NumberViewModel : ViewModel() {

    private val _randomNumberLiveData = MutableLiveData<Int>()
    val randomNumberLiveData: LiveData<Int>
        get() = _randomNumberLiveData
    private var isRunning = false

    fun getRandomNumbers() {
        object : Thread() {
            override fun run() {
                isRunning = true
                while (isRunning) {
                    try {
                        val randomNumber = Random.nextInt(0, 100)
                        _randomNumberLiveData.postValue(randomNumber)
                        sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
        isRunning = false
    }
}