package com.celalkorucu.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        CountDownTimer timer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                textView.setText("Left : "+l/1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}