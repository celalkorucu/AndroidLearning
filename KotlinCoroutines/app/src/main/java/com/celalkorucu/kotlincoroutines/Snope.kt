package com.celalkorucu.kotlincoroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(){

    runBlocking {
        launch {
            delay(5000)
            println("runBlocking")
        }

        coroutineScope {
            delay(3000)
            println("coroutinesScope")
        }
    }


    //Light Weightness

    /*
    GlobalScope.launch {
        repeat(100000){
            launch {
                println("Android")
            }
        }
    }

     */

    //Scope
    //Global Scope , runBlocking , CoroutinesScope

    /*
    //runBlocking
    println("runBlocking start")
    runBlocking {
        launch {
        delay(5000)
            println("runBlocking")
        }
    }
    println("runBlocking stop")

    //GlobalScope

    println("GlobalScope start")
    GlobalScope.launch {
        delay(5000)
        println("GlobalScope")
    }
    println("GlobalScope stop")




    //CoroutinesScope
    //Context
    println("Coroutines start")
    CoroutineScope(Dispatchers.Default).launch {
        delay(5000)
        println("CoroutinesScope")
    }
    println("Coroutines stop")

    */


}