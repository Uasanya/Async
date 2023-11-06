package com.example.async

import kotlin.random.Random

class Repository {

    fun getRandom() = Random.nextInt(0, 100).toString()

}