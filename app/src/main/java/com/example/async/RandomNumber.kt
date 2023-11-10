package com.example.async

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlin.random.Random

class RandomNumber {

    fun generateNumbersList(): Observable<List<Int>> {
        var list = listOf<Int>()
        return Observable
            .interval(1, java.util.concurrent.TimeUnit.SECONDS)
            .map {
                val randomNumber = Random.nextInt(100)
                list = list + randomNumber
                list
            }
            .subscribeOn(Schedulers.io())
    }
}