package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Intent diceScreen, coinScreen, timerScreen;

    ImageView titleBoardy, diceone, dicetwo, background, Truong;
    Animation fadein, diceonemovetomiddle, dicetwomovetomiddle, diceonemovetonew, bounce, blink, uptodown, uptodownCoin, uptodownTimer;
    Button diceButton, coinflipButton, timerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent
        diceScreen = new Intent(getApplicationContext(), DiceRoll.class);
        coinScreen = new Intent(getApplicationContext(), CoinFlip.class);
        timerScreen = new Intent(getApplicationContext(), TimerScreen.class);

        //ImageView
        background = (ImageView)findViewById(R.id.background);
        titleBoardy = (ImageView)findViewById(R.id.titleBoardy);
        Truong = (ImageView)findViewById(R.id.truong);
        diceone = (ImageView)findViewById(R.id.diceone);
        dicetwo = (ImageView)findViewById(R.id.dicetwo);

        //Animations
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        diceonemovetomiddle = AnimationUtils.loadAnimation(this, R.anim.diceonemovetomiddle);
        dicetwomovetomiddle = AnimationUtils.loadAnimation(this, R.anim.dicetwomovetomiddle);
        blink = AnimationUtils.loadAnimation(this, R.anim.blink_anim);
        diceonemovetonew = AnimationUtils.loadAnimation(this, R.anim.diceonemovetonew);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        uptodownCoin = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        uptodownTimer = AnimationUtils.loadAnimation(this, R.anim.uptodown);

        //Buttons
        diceButton = (Button)findViewById(R.id.dice);
        coinflipButton = (Button)findViewById(R.id.coins);
        timerButton = (Button)findViewById(R.id.timer);

        titleBoardy.setAlpha(0);
        Truong.setAlpha(0);
        diceButton.setAlpha(0);
        coinflipButton.setAlpha(0);

        diceone.startAnimation(diceonemovetonew);
        dicetwo.startAnimation(dicetwomovetomiddle);

        dicetwomovetomiddle.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                titleBoardy.setAlpha(255);
                titleBoardy.startAnimation(fadein);



                fadein.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Truong.setAlpha(255);
                        Truong.startAnimation(bounce);

                        bounce.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                diceone.startAnimation(blink);
                                dicetwo.startAnimation(blink);
                                diceButton.setAlpha(255);
                                diceButton.startAnimation(uptodown);


                                uptodown.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {

                                        coinflipButton.setAlpha(255);
                                        coinflipButton.startAnimation(uptodownCoin);

                                        uptodownCoin.setAnimationListener(new Animation.AnimationListener() {
                                            @Override
                                            public void onAnimationStart(Animation animation) {

                                            }

                                            @Override
                                            public void onAnimationEnd(Animation animation) {

                                                timerButton.setAlpha(255);
                                                timerButton.startAnimation(uptodownTimer);
                                                coinflipButton.clearAnimation();

                                            }

                                            @Override
                                            public void onAnimationRepeat(Animation animation) {

                                            }
                                        });


                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




        diceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(diceScreen);
            }
        });

        coinflipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(coinScreen);

            }
        });

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(timerScreen);
            }
        });


    }
}
