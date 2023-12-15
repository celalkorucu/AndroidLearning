package com.celalkorucu.kotlinlandmarkbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.celalkorucu.kotlinlandmarkbook.databinding.ActivityMainBinding

var arrayList = ArrayList<Landmark>()

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view : View = binding.root
        setContentView(view)


        var eiffel = Landmark("Eiffel" , "France" , R.drawable.eiffel)
        var ayasofya = Landmark("Ayasofya" , "İstanbul" , R.drawable.ayasofya)
        var anitkabir = Landmark("Anıtkabir" , "Ankara" , R.drawable.anitkabir)

        arrayList.add(eiffel)
        arrayList.add(ayasofya)
        arrayList.add(anitkabir)



        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        var adapter = LandmarkAdapter(arrayList)
        binding.recyclerView.adapter = adapter




    }
}