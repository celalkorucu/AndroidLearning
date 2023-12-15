package com.celalkorucu.kotlincoroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    runBlocking {
        delay(2000)
        println("runBlocking")
        myFunction()

    }


}

suspend fun myFunction(){

    coroutineScope {
        delay(5000)
        println("suspend Function")
    }
}