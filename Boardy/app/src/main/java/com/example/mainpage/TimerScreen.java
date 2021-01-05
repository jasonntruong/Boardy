package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TimerScreen extends AppCompatActivity {

    EditText minutes, seconds;
    Button buttonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_screen);

        buttonTest = (Button) findViewById(R.id.button);
        minutes = (EditText)findViewById(R.id.minutes);
        seconds = (EditText)findViewById(R.id.seconds);

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int secondsInt = Integer.valueOf(String.valueOf(minutes.getText()))*60 + Integer.valueOf(String.valueOf(seconds.getText()));
                Log.d("Number of Minutes", String.valueOf(minutes.getText()));
                Log.d("Number of Seconds", String.valueOf(secondsInt));

            }
        });




    }
}