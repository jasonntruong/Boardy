package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    ImageView titleBoardy, diceone, dicetwo, background, Truong;
    Animation fadein, diceonemovetomiddle, dicetwomovetomiddle, diceonemovetonew, downtoup, blink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Truong = (ImageView)findViewById(R.id.truong);

        titleBoardy = (ImageView)findViewById(R.id.titleBoardy);
        diceone = (ImageView)findViewById(R.id.diceone);
        dicetwo = (ImageView)findViewById(R.id.dicetwo);

        background = (ImageView)findViewById(R.id.background);


        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        diceonemovetomiddle = AnimationUtils.loadAnimation(this, R.anim.diceonemovetomiddle);
        dicetwomovetomiddle = AnimationUtils.loadAnimation(this, R.anim.dicetwomovetomiddle);
        blink = AnimationUtils.loadAnimation(this, R.anim.blink_anim);

        diceonemovetonew = AnimationUtils.loadAnimation(this, R.anim.diceonemovetonew);

        titleBoardy.setAlpha(0);
        Truong.setAlpha(0);
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
                        Truong.startAnimation(downtoup);

                        downtoup.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                diceone.startAnimation(blink);
                                dicetwo.startAnimation(blink);
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
}
