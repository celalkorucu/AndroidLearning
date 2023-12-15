package com.celalkorucu.kotlinoop

class SuperMusician(name: String, surname: String, age: Int) : Musician(name, surname, age) {

    fun sing(){
        println("Bir gün için bir ömrü ziyan ettin")
    }
}