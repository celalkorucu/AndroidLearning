package com.celalkorucu.kotlinadvancedfuncitons

fun main(){

    var myList = listOf<Int>(1,2,4,34,2,44,54,4)

    var newList = myList.filter {
        it<6
    }
    for(i in newList){
        print("$i , ")
    }
    println()

    var squaredList = myList.map {
        it*it
    }

    for(i in squaredList){
        print("$i - ")
    }

    var musiciansList = listOf<Musician>(
        Musician("Celal" , 21 , "Piyano"),
        Musician("İrem" , 22 , "Keman"),
        Musician("Serhat" , 21 , "Gitar")
    )

    val newMusiciansList  = musiciansList.filter { it.age<22 }.map { it.instument }

    println()
    for(element in newMusiciansList){
        print("$element - ")
    }
}

class Musician (var name : String , var age : Int , var instument : String){

}