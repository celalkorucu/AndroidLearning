package com.celalkorucu.runnablehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Runnable runnable ;
    Handler handler;
    Button button ;

    Button  button2 ;

    Button button3 ;
    TextView textView ;
    int number ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        number = 0 ;
        button = findViewById(R.id.btn_start);
        button2 = findViewById(R.id.btn_stop);
        button3 = findViewById(R.id.button3);

        button2.setEnabled(false);

    }


    public void start(View view){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                textView.setText("Time : "+number);
                number++;
                textView.setText("Time : "+number);

                handler.postDelayed(runnable ,1000);


            }

        };
        button.setEnabled(false);
        button2.setEnabled(true);
        handler.post(runnable);


    }

    public void stop(View view){
        button.setEnabled(true);
        handler.removeCallbacks(runnable);
        button2.setEnabled(false);
        button3.setEnabled(true);
    }

    public void reset(View view){
        number = 0;
        textView.setText("Time : "+number);
    }
}