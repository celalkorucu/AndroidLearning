package com.celalkorucu.kotlinlandmarkbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.celalkorucu.kotlinlandmarkbook.databinding.ActivityDetailsBinding

lateinit var  binding2 : ActivityDetailsBinding
class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityDetailsBinding.inflate(layoutInflater)
        val view : View = binding2.root
        setContentView(view)


        val intent = intent
        /*
        val name = intent.getStringExtra("name")
        val country = intent.getStringExtra("country")
        val image = intent.getIntExtra("image" ,0)

        binding2.nameTextView.text = name
        binding2.countryText.text = country
        binding2.imageView.setImageResource(image)

         */


       var landmark = intent.getSerializableExtra("landmark") as Landmark
        binding2.imageView.setImageResource(landmark.image)
        binding2.nameTextView.text = landmark.name
        binding2.countryText.text = landmark.country
    }
}