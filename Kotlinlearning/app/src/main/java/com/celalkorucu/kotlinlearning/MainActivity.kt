package com.celalkorucu.kotlinlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myNumber : Int = 5 ;

        println(myNumber)

        myNumber ++
        println(myNumber)

        val myString : String = "celalkorucu"

        println(myString)


        println("--------------Arrays----------------")
        var myArray = arrayOf("Tyrion" , "Daenarys " , "Jon" , "Cersie")

        println(myArray[0])
        println(myArray[1])
        myArray[1] = "Daenarys Targeryan"
        println(myArray[1])

        var myArray2 = arrayOf(1 ,2,3 ,"celal")
        println(myArray2[0])
        println(myArray2[3])

        println("-------------ArrayList--------------")

        val myArrayList = arrayListOf<String>("celal","korucu")
        println(myArrayList[0])

        val newArray = ArrayList<Int>()
        newArray.add(1)
        newArray.add(2)
        println(newArray)

        val mixedArray = ArrayList<Any>()
        mixedArray.add("Celal")
        mixedArray.add(21)
        mixedArray.add(false)

        println(mixedArray)

        println("-------------Set--------------")

        var mySet = setOf<Int>(1,1,2,3,4)
        println(mySet)
        println("mySet : ${mySet.size} df")

        var myExampleSet = HashSet<String>()
        myExampleSet.add("Targeryan")
        myExampleSet.add("Targeryan")
        myExampleSet.add("Lannister")
        println(myExampleSet)

        //For Each
        myExampleSet.forEach { println(it) }


        println("------------Map--------------")

        var myMap = HashMap<String,String>()
        myMap.put("Targeryan","Daenarys")
        myMap.put("Lannister" ,"Tyrion")
        myMap.put("Stark" , "Eddard")
        println(myMap)

        myMap.forEach { println(it)  }

        var myNewMap = mapOf<String,Int>("A" to 1 , "B" to 2 , "C" to 3)
        println(myNewMap)

        println("----------------When--------------")

        var day : Int = 3 ;
        var dayString : String = ""

        when(day){
            1-> dayString = "Monday"
            2-> dayString = "Tuesday"
            3 -> dayString = "Wednesday"
            else  -> dayString = ""
        }
        println(dayString)


        println("-----------------For--------------")
         var myForArray = arrayListOf<String>("Stark","Lannister", "Targeryan")


        for(family in myForArray ){
            println(family)
        }
        var myNewForArray = ArrayList<Int>()
        myNewForArray.add(1)
        myNewForArray.add(2)
        myNewForArray.add(3)
        myNewForArray.add(4)

        for(number in myNewForArray){
            var num = number*10
            println(num)
        }



    }
}