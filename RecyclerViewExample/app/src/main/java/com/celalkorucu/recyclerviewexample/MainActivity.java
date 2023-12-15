package com.celalkorucu.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.celalkorucu.recyclerviewexample.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding ;
    ArrayList<String> list ;
RCAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        list = new ArrayList<>();


        list.add("Celal KORUCU");
        list.add("İsmail KORUCU");
        list.add("Havva KORUCU");
        list.add("Halime Nur KORUCU");
        list.add("Fatma Sena KORUCU");
        list.add("Mustafa KORUCU");
        list.add("Serhat YÖRÜK");
        list.add("Meryem DEMİRÖRS");
        list.add("Demirci MEHMET");
        list.add("Zeynep Sude KEÇECİ");
        list.add("Melisa ÇAKICI");

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RCAdapter(list);


binding.recyclerView.setAdapter(adapter);







    }


}