package com.celalkorucu.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.celalkorucu.sharedpreferences.databinding.ActivityMainBinding


lateinit var binding : ActivityMainBinding
lateinit var sharedPref : SharedPreferences
var ageFromPref : Int? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

         sharedPref = this.getSharedPreferences("com.celalkorucu.sharedpreferences" , Context.MODE_PRIVATE)

        ageFromPref = sharedPref.getInt("age" , -1)

        if(ageFromPref != -1){
            binding.ageText.text = "Your Age : ${ageFromPref}"
        }else{
            binding.ageText.text = "Your Age : "
        }


    }

    fun save(view : View){

        var age = binding.ageEditText.text.toString().toIntOrNull()
        if(age != null){
            sharedPref.edit().putInt("age" , age).apply()
            binding.ageText.text = "Your Age : ${age}"
        }else{
            binding.ageText.text = "Your age : "
        }


    }
    fun delete(view : View){

      if(ageFromPref != -1){
          sharedPref.edit().remove("age").apply()
          binding.ageText.text = "Your Age : "
      }
    }
}