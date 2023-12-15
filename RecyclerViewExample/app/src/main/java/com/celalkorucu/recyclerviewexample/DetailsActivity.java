package com.celalkorucu.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.celalkorucu.recyclerviewexample.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {


    ActivityDetailsBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(LayoutInflater.from(this));
        View view = binding.getRoot();
        setContentView(view);


        Intent intent = getIntent();
        String name = intent.getStringExtra("data");
        binding.textView.setText(name);


    }
}