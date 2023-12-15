package com.celalkorucu.kotlinadvancedfuncitons

fun main(){
    val myNumber : Int? = null

    //myNumber ın değeri null değil ise let içerisi çalışır
    myNumber?.let {
        val num = it+1
    }

    var myNumber2 =myNumber?.let {
        it+1
    }?:0


    val atil = Person("Atıl" ,35)
    val ismail = Person("İsmail" , 43)
    val celal = Person("Celal" , 21)

    var list = listOf<Person>(atil , ismail , celal)

    list.filter { it.age > 22 }.also {
        for(i in it){
            println(i.name)
        }
    }



}

class Person(var name : String , var age : Int){

}