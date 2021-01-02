package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class CoinFlip extends AppCompatActivity {

    Button coin;
    String coinResultString;
    Animation zoomin, blink;
    TextView coinResultView, instructions;

    private void onAnimationComplete (final AnimationDrawable anim, final String coinResultString){
        zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        coinResultView = findViewById(R.id.coinResultView);
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                if (anim.getCurrent() != anim.getFrame(anim.getNumberOfFrames() - 1)){
                    onAnimationComplete(anim, coinResultString);
                }else{

                    coinResultView.setAlpha(255);

                    coinResultView.setText(String.valueOf(coinResultString));
                    coinResultView.startAnimation(zoomin);

                    zoomin.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            coinResultView.setAlpha(0);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        }, timeBetweenChecks);
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flip);

        blink = AnimationUtils.loadAnimation(this, R.anim.blink_instructions);

        coin = findViewById(R.id.coin);
        instructions = findViewById(R.id.instructions);

        instructions.startAnimation(blink);



        coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (instructions.getAlpha() != 0){
                    instructions.setAlpha(0);
                    instructions.clearAnimation();
                }
                coin.setBackgroundResource(R.drawable.coina);
                Random coinPick = new Random();



                int coinResult = coinPick.nextInt(2)+1;


                if (coinResult == 1) {
                    coin.setBackgroundResource(R.drawable.coinfliphead); //1 means head
                    coinResultString = "HEADS";
                }

                else if (coinResult == 2){
                    coin.setBackgroundResource(R.drawable.coinfliptail); //2 means tail
                    coinResultString = "TAILS";
                }

                Log.d("Coin value", String.valueOf(coinResult));

                AnimationDrawable anim = (AnimationDrawable) coin.getBackground();
                anim.start();
                onAnimationComplete(anim, coinResultString);
            }
        });


    }
}
