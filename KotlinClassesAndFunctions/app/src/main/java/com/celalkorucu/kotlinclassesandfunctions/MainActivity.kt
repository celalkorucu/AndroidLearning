package com.celalkorucu.kotlinclassesandfunctions

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var myTextView  : TextView
    lateinit var myButton : Button
    private  lateinit var nameText: TextView
    private  lateinit var  ageText: TextView
    private  lateinit var  jobText : TextView

    lateinit var name : String
    lateinit var job : String


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myTextView = findViewById(R.id.textView)
        myButton = findViewById(R.id.button)
        nameText = findViewById(R.id.nameText)
        ageText = findViewById(R.id.ageText)
        jobText = findViewById(R.id.jobText)





/*
        myButton.setOnClickListener{
            myTextView.text = "Button Clicked"
        }

 */

        /*
        var fruit = Fruit()
        fruit.color = "Green"
        println(fruit.color)


         */
/*
        var fruit = Fruit("Yellow " , 30)
        println(fruit.color + " " + fruit.price)
        fruit.setColor()
        println(fruit.color + " " + fruit.price)


 */

        //Nullability
        //Boş gelebilecek değerler için kullanılır

        // !!  değer kesinlikle null değil demektir
        // ? null olabilir
        /*
        var myString : String? = null
      //  myString = "Celal Korucu"
println(myString)


        var myAge : Int? = null
        myAge = 50

        // 1)
        println(myAge!! *10)


        // 2) safe call
        println(myAge?.minus(10))


        //3
        if(myAge != null){
            println(myAge.minus(10))
        }else{
            println("myAge is null")
        }




        // 4 elvis operator
        // soldaki değer null değilse işlemi yapar null ise sağdaki işlemi yapar
        println(myAge?.minus(10) ?: -10)




        // 5)
        myAge.let {
            println(it.minus(10))
            println(it * 10)
        }

         */







    }



    fun Test(){
        println("Hello Kotlin")
    }

    fun sum(a : Int , b : Int){
        var sum = a+b
        println(sum)
    }

    fun  summ(a : Int , b : Int) : Int{
        return a+b
    }

    fun buttonClicked (view : View){
        name = nameText.text.toString()
       var  age = ageText.text.toString().toIntOrNull()
        job = jobText.text.toString()




        if(age != null){
            var user = User(name , age , job)
            myTextView.text ="Name : ${user.name}  Age : ${user.age}  Job : ${user.job}"

        }else{
            myTextView.text ="Enter your age"
        }





    }


}