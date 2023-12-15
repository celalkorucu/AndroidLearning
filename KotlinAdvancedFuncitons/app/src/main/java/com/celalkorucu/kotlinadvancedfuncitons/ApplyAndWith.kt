package com.celalkorucu.kotlinadvancedfuncitons

import android.content.Intent

fun main(){
    // Apply genelde bir nesneyi tanımlarken kullanılır

    /*
    val intent = Intent()
    intent.`package` = ""
    intent.putExtra("asd" ,"")


     */

    /*
    //Apply
    val intentApply = Intent().apply {
        `package`=""
        putExtra("","")
        putExtra("","")
    }

     */

    //With
    Person("celal",2).apply {
        name = "Fatma Sena"
        age = 9
    }

    val anotherDog = with(Person("Dog", 4)) {
        name = "Barley"
    }

    val withBarley = Person("Dog",4)
    with(withBarley){
        name = "Barley"
    }

    println(withBarley.name)
}
