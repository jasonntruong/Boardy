package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Objects;
import java.util.Random;

public class DiceRoll extends AppCompatActivity {


    ImageView dice, diceshadow;
    TextView instructions, rolled;
    Animation blink, zoomin;

    SensorManager shakeDetect;
    float sensorAccel, sensorAccelPast, sensorAccelCurrent;


    private void onAnimationComplete (final AnimationDrawable anim, final int num){
        zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        rolled = findViewById(R.id.rolled);
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                if (anim.getCurrent() != anim.getFrame(anim.getNumberOfFrames() - 1)){
                    onAnimationComplete(anim, num);
                } else{

                    rolled.setAlpha(255);

                    rolled.setText(String.valueOf(num));
                    rolled.startAnimation(zoomin);

                    zoomin.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            rolled.setAlpha(0);
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
        setContentView(R.layout.activity_dice_roll);

        blink = AnimationUtils.loadAnimation(this, R.anim.blink_instructions);

        instructions = (TextView)findViewById(R.id.instructions);
        rolled = findViewById(R.id.rolled);
        diceshadow = findViewById(R.id.diceshadow);

        shakeDetect = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        shakeDetect.registerListener(shakeDetectListener, shakeDetect.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        diceshadow.setAlpha(0);
        instructions.startAnimation(blink);


        sensorAccel = 10f;
        sensorAccelPast = SensorManager.GRAVITY_EARTH;
        sensorAccelCurrent = SensorManager.GRAVITY_EARTH;


    }

    private SensorEventListener shakeDetectListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            sensorAccelPast = sensorAccelCurrent;
            sensorAccelCurrent = (float) Math.sqrt((double) (x*x+y*y+z*z)); //force of shake
            float delta = sensorAccelCurrent - sensorAccelPast; //change in force of shake

            sensorAccel = sensorAccel*0.9f + delta;

            if (sensorAccel > 15){

                if (instructions.getAlpha() != 0) {
                    diceshadow.setAlpha(255);
                    instructions.setAlpha(0);
                    instructions.clearAnimation();
                }


                dice = (ImageView)findViewById(R.id.dice);
                dice.setBackgroundResource(R.drawable.dicefd);

                Random dicePicker = new Random();

                int diceNumber = dicePicker.nextInt(6) + 1;
                Log.d("My dice number", String.valueOf(diceNumber));

                if (diceNumber == 1)    dice.setBackgroundResource(R.drawable.dicerollone);

                else if (diceNumber == 2)   dice.setBackgroundResource(R.drawable.dicerolltwo);

                else if (diceNumber == 3)   dice.setBackgroundResource(R.drawable.dicerollthree);

                else if (diceNumber == 4)   dice.setBackgroundResource(R.drawable.dicerollfour);

                else if (diceNumber == 5)   dice.setBackgroundResource(R.drawable.dicerollfive);

                else if (diceNumber == 6)   dice.setBackgroundResource(R.drawable.dicerollsix);

                AnimationDrawable anim = (AnimationDrawable) dice.getBackground();
                anim.run();
                onAnimationComplete(anim, diceNumber);

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}