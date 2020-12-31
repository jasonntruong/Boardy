package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;

public class DiceRoll extends AppCompatActivity {

    Button testbutton;
    ImageView dice, dicea;


    public void diceRoll2() {

        dice = (ImageView)findViewById(R.id.dice);
        dice.setBackgroundResource(R.drawable.diceaa);
        dice.setBackgroundResource(R.drawable.dicerolltwo);

        new Thread(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AnimationDrawable anim = (AnimationDrawable) dice.getBackground();
                        anim.start();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roll);


        testbutton = (Button)findViewById(R.id.testbutton);
        dice = (ImageView)findViewById(R.id.dice);


        testbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dice = (ImageView)findViewById(R.id.dice);
                dice.setBackgroundResource(R.drawable.diceaa);

                Random dicePicker = new Random();

                int diceNumber = dicePicker.nextInt(6) + 1;
                Log.d("My dice number", String.valueOf(diceNumber));

                if (diceNumber == 1)    dice.setBackgroundResource(R.drawable.dicerollfull);

                else if (diceNumber == 2)   dice.setBackgroundResource(R.drawable.dicerolltwo);

                else if (diceNumber == 3)   dice.setBackgroundResource(R.drawable.dicerollthree);

                else if (diceNumber == 4)   dice.setBackgroundResource(R.drawable.dicerollfour);

                else if (diceNumber == 5)   dice.setBackgroundResource(R.drawable.dicerollfive);

                else if (diceNumber == 6)   dice.setBackgroundResource(R.drawable.dicerollsix);

                AnimationDrawable anim = (AnimationDrawable) dice.getBackground();
                anim.run();

            }







        });
    }
}
