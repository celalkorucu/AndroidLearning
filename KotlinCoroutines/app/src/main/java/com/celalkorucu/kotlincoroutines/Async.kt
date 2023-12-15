package com.celalkorucu.kotlincoroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    runBlocking {

        var name = ""
        var age = 0
        val downloadedName = async {
            downloadName()
        }
        val downloadedAge  = async {
            downloadAge()
        }

        name = downloadedName.await()
        age = downloadedAge.await()
        println("${name} : ${age}")


    }
}

suspend fun downloadName() : String {
    delay(2000)
    val userName = "Celal"
    println("userName download")
    return userName
}

suspend fun downloadAge() : Int{
    delay(4000)
    val userAge = 21
    println("userAge download")
    return userAge
}