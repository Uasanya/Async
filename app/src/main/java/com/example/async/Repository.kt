package com.example.async

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class Repository {

    suspend fun getRandomFlow() : Flow<Int> = flow {
        while (true) {
            val number = getRandom()
            emit(number)
            delay(1000L)
        }
    }

    private fun getRandom() = Random.nextInt(0, 100)
}