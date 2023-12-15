package com.celalkorucu.kotlincalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


 private lateinit var firstNumber : EditText
private lateinit var secondNumber : EditText
private lateinit var sumButton : Button
private lateinit var subButton : Button
private lateinit var multButton : Button
private lateinit var divButton : Button
private lateinit var result : TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstNumber = findViewById(R.id.firstNumberText)
        secondNumber = findViewById(R.id.secondNumberText)
        sumButton = findViewById(R.id.sumButton)
        subButton = findViewById(R.id.subButton)
        divButton = findViewById(R.id.divButton)
        multButton = findViewById(R.id.multButton)
        result = findViewById(R.id.resultText)

    }

    fun sum(view : View){
        var f = firstNumber.text.toString().toIntOrNull()
        var s = secondNumber.text.toString().toIntOrNull()


 var sum = 0
        if(f != null && s != null){
             sum = f+s!!
            result.text = "Result : ${sum}"
        }else{
            result.text  = "Enter Number"
        }


    }
    fun sub(view : View){

        var f = firstNumber.text.toString().toIntOrNull()
        var s = secondNumber.text.toString().toIntOrNull()


        var sum = 0
        if(f != null && s != null){
            sum = f-s!!
            result.text = "Result : ${sum}"
        }else{
            result.text  = "Enter Number"
        }

    }
    fun mult(view : View){
        var f = firstNumber.text.toString().toIntOrNull()
        var s = secondNumber.text.toString().toIntOrNull()


        var sum = 0
        if(f != null && s != null){
            sum = f*s!!
            result.text = "Result : ${sum}"
        }else{
            result.text  = "Enter Number"
        }
    }
    fun dive(view : View){

        var f = firstNumber.text.toString().toIntOrNull()
        var s = secondNumber.text.toString().toIntOrNull()


        var sum = 0
        if(f != null && s != null){
            sum = f/s!!
            result.text = "Result : ${sum}"
        }else{
            result.text  = "Enter Number"
        }
    }


}