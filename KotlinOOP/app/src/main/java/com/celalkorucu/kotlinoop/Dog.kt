package com.celalkorucu.kotlinoop

class Dog : Animal() {

    fun test (){
        //super : inheritance'teki metoda referans verir
        //sing metodu = animaldeki sing metodu
        super.sing()
    }
    override fun sing(){
        println("Hello Dog")
    }
}