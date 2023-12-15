package com.celalkorucu.kotlinoop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var user = User("Khal" , "Drogo" , 34)
        user.age = 35
        println(user.name + " " + user.surname + " " + user.age)


        var musician  = Musician("Celal" , "Korucu" , 20)
        println(musician.name)
        musician.name = "İsmail"
        println(musician.name)



        var debubluman = SuperMusician("Debüblüman" , "Korucu" , 43)
        println(debubluman.name)
        println(debubluman.surname)
        println(debubluman.age)

        debubluman.sing()

        debubluman.name = "Celal"
        debubluman.surname = "KORUCU"
        debubluman.age = 20

        println(debubluman.name)
        println(debubluman.surname)
        println(debubluman.age)

        debubluman.sing()


        var animal = Animal()
        var dog = Dog()


        animal.sing()
        dog.test()
        dog.sing()



        var piano = Piano("Guitar" , false)
       // println(piano.brand)
        //println(piano.digital)
        //println(piano.roomName)
        piano.brandInfo("Guitar")
        piano.digitalInfo(true)

        println(piano.brandInfo("Guitar"))

        println(piano.digitalInfo(true))

        var m = {myString : String , myString2 : String -> println(myString + " " + myString2) }
        m("a","b")

        var sum = {a : Int , b: Int -> a+b}
       var result =  sum(10 ,10)
        println(result)

        var dive : (Int , Int) ->Int  =  {a,b -> a/b}
        println(dive(100,5))
    }
}