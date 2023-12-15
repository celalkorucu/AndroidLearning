package com.celalkorucu.kotlinadvancedfuncitons

fun main(){

    var myList = listOf(1,2,4,34,2,44,54,4)

    //Hepsi 5 den büyük mü ?
    val allCheck = myList.all { it > 5 }
    println(allCheck)
    //Herhangi biri 5 den büyük mü ?
    val anyCheck = myList.any { it > 5 }
    println(anyCheck)
    //5 den büyük kaç adet veri var
    val countCheck = myList.count { it > 5 }
    println(countCheck)
    //5 den büyük ilk değer
    val findNum = myList.find { it > 5 }
    println(findNum)
    //5 den büyük en son değer
    val findLastNum = myList.findLast { it > 5 }
    println(findLastNum)
    //Önceden bir kontrolü tanımlayabiliyoruz
    var myPredicate = {num : Int -> num > 5}
    //Tanımladığımız ifadeyi bu şekilde kullanabiliyoruz
    val allCheckWithPredicate = myList.all(myPredicate)
    println(allCheckWithPredicate)

}