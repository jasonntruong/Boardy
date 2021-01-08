package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;


import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class TimerScreen extends AppCompatActivity {

    EditText minutes, seconds;
    Button buttonTest, buttonstopTest, timerComplete;
    int currentMinute, currentSecond, secondsInt;
    Intent backtomainTemp;
    CountDownTimer timer;
    Animation righttoleft, lefttoright;
    TextView minuteWord, secondsWord;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_screen);

        righttoleft = AnimationUtils.loadAnimation(this, R.anim.righttoleft);
        lefttoright = AnimationUtils.loadAnimation(this, R.anim.lefttoright);

        buttonTest = (Button) findViewById(R.id.button);
        buttonstopTest = findViewById(R.id.button2);
        timerComplete = findViewById(R.id.button3);

        minutes = (EditText)findViewById(R.id.minutes);
        seconds = (EditText)findViewById(R.id.seconds);

        minuteWord = findViewById(R.id.minuteWord);
        secondsWord = findViewById(R.id.secondsWord);

        backtomainTemp = new Intent(getApplicationContext(), MainActivity.class);



        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonTest.setClickable(false);

                if (String.valueOf(minutes.getText()).equals("")) {
                    minutes.setText("0");
                }

                if (String.valueOf(seconds.getText()).equals("")) {
                    seconds.setText("0");
                }

                currentMinute = Integer.valueOf(String.valueOf(minutes.getText()));
                currentSecond = Integer.valueOf(String.valueOf(seconds.getText())) + 1;

                secondsInt = currentMinute * 60 + currentSecond;

                timer = new CountDownTimer(secondsInt*1000, 1000){



                    @Override
                    public void onTick(long millisUntilFinished) {

                        if (currentSecond == 0) {
                            if (currentMinute > 0) {
                                currentMinute -= 1;
                            }

                            currentSecond = 59;

                        }
                        else if (currentSecond > 0){
                            currentSecond -=1;
                        }

                        secondsInt = currentMinute * 60 + currentSecond;
                        minutes.setText(String.valueOf(currentMinute));
                        seconds.setText(String.valueOf(currentSecond));
                        Log.d("SecondsInt", String.valueOf(secondsInt));
                    }

                    @Override
                    public void onFinish() {
                        minuteWord.startAnimation(righttoleft);
                        secondsWord.startAnimation(righttoleft);
                        minutes.startAnimation(righttoleft);
                        seconds.startAnimation(righttoleft);
                        buttonTest.startAnimation(righttoleft);
                        buttonstopTest.startAnimation(righttoleft);

                        buttonTest.setClickable(true);

                        righttoleft.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                minuteWord.setAlpha(0);
                                secondsWord.setAlpha(0);
                                minutes.setAlpha(0);
                                seconds.setAlpha(0);
                                buttonTest.setAlpha(0);
                                buttonstopTest.setAlpha(0);
                                timerComplete.setAlpha(255);
                                timerComplete.startAnimation(lefttoright);
                                timerComplete.setClickable(true);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });


                    }
                }.start();

            }

        });

        buttonstopTest.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try{
                    timer.cancel();
                } catch (NullPointerException e) {

                } finally {
                    buttonTest.setClickable(true);
                }

                return false;
            }
        });

        timerComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timerComplete.startAnimation(righttoleft);
                righttoleft.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        minuteWord.setAlpha(255);
                        secondsWord.setAlpha(255);
                        minutes.setAlpha(255);
                        seconds.setAlpha(255);
                        buttonTest.setAlpha(255);
                        buttonstopTest.setAlpha(255);
                        minuteWord.startAnimation(lefttoright);
                        secondsWord.startAnimation(lefttoright);
                        minutes.startAnimation(lefttoright);
                        seconds.startAnimation(lefttoright);
                        buttonTest.startAnimation(lefttoright);
                        buttonstopTest.startAnimation(lefttoright);

                        timerComplete.setAlpha(0);
                        timerComplete.setClickable(false);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });


    }
}