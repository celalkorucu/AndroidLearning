package com.celalkorucu.kotlinadvancedfuncitons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity(),LifecycleLogger by LifecycleLoggerImplementation() {

    //lazy bellekte yer kaplamaması için kullanılır
    //İhtiyaç olunmadıkça değişken kullanılmaz
    private val myNumber by lazy {
        println("init")
        10
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(myNumber)
        registerLifecycleOwner(this@MainActivity)
    }
}

interface LifecycleLogger {
    fun registerLifecycleOwner(owner : LifecycleOwner)
}

class LifecycleLoggerImplementation : LifecycleLogger, LifecycleEventObserver {
    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this@LifecycleLoggerImplementation)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

        when(event){
            Lifecycle.Event.ON_RESUME -> println("onResume")
            Lifecycle.Event.ON_PAUSE  -> println("onPause")
            else -> Unit
        }
    }

}
