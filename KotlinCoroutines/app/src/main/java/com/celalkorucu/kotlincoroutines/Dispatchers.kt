package com.celalkorucu.kotlincoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(){

    //Dispatchers
    //Dispatchers.Default  -> CPU Intensive (Büyük işlemler)
    //Dispatchers.IO -> Input / Output , Networking (Retrofit vb)
    //Dispatchers.Main -> UI
    //Dispatchers.Unconfined -> Inherited dispatchers


    runBlocking {
        /*
        launch (Dispatchers.Main){
            println("Main Thread ${Thread.currentThread().name}")
        }

         */

        launch(Dispatchers.IO) {
            println("IO Thread  ${Thread.currentThread().name}")

        }

        launch (Dispatchers.Default){
            println("Default Thread ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Unconfined) {
            println("Unconfined Thread  ${Thread.currentThread().name}")

        }
    }
}