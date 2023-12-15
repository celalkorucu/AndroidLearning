package com.celalkorucu.runnablekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.celalkorucu.runnablekotlin.databinding.ActivityMainBinding

lateinit var  binding : ActivityMainBinding
var runnable : Runnable = Runnable {}
var handler : Handler = Handler(Looper.getMainLooper())
var number : Int = 0
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view  : View  = binding.root
        setContentView(view)


    }


    fun start ( view : View){

        number = 0
       runnable  = object : Runnable {
           override fun run() {
               number ++
               binding.timeText.text = "Time : ${number}"
               handler.postDelayed(runnable  , 1000)

           }
       }
        handler.post( runnable)
        binding.startButton.isEnabled = false
        binding.stopButton.isEnabled = true
    }
    fun stop (view : View){

        binding.startButton.isEnabled = true
        binding.stopButton.isEnabled = false
        handler.removeCallbacks(runnable)

    }
}