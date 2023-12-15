package com.celalkorucu.kotlincoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(){

    //Bu işlem genelde IO ile başlayan bir şeyi Main ile dewam ettirebilmek için kullanırız
    //JOB
    runBlocking {

        launch (Dispatchers.Default) {
            println("Context : $coroutineContext")

            withContext(Dispatchers.IO){
                println("Context : $coroutineContext")
            }
        }
    }
}