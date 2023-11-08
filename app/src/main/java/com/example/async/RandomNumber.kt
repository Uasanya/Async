package com.example.async

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class RandomNumber :Thread() {

    private val _randomNumberLiveData = MutableLiveData<List<Int>>()
    val randomNumberLiveData : LiveData<List<Int>>
        get() = _randomNumberLiveData
    private var isRunning = false
    private var list = ArrayList<Int>()

    fun startGeneratingRandomNumbers() {
        isRunning = true
        start()
    }

    fun stopGeneratingRandomNumbers() {
        isRunning = false
        list.clear()
    }

    override fun run() {
        while (isRunning) {
            val randomNumber = Random.nextInt(0, 100)
            list.add(randomNumber)
            _randomNumberLiveData.postValue(list)
        }
            try {
                sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
