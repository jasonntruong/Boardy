package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainScoreSheet extends AppCompatActivity {

    Intent scoresheet;
    Bundle fileBundle;

    File path, file, dir;

    File[] allFiles;

    List<Button> allButtons;

    Button scoresheet1, scoresheet2, scoresheet3, scoresheet4, scoresheet5, currentButton, delete, open, add;

    protected void getAllFiles(File[] allFiles){
        for (int i = 0; i < allFiles.length; i++){
            Log.d("File name", allFiles[i].getName());
        }
    }

    protected void addButton(File file, Button button){
        button.setAlpha(255);
        button.setEnabled(true);
        button.setText(file.getName().replace(".txt", ""));
    }

    protected void buttonSetup(File[] allFiles, List<Button> allButtons){
        for (int i = 0; i < allFiles.length; i++){
            addButton(allFiles[i], allButtons.get(i));
        }
    }

    protected void deleteAllFiles(File[] allFiles){
        for (int i = 0; i < allFiles.length; i++){
            allFiles[i].delete();
            Log.d("Deleted", allFiles[i].getName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_score_sheet);

        scoresheet = new Intent(getApplicationContext(), ScoreSheet.class);
        fileBundle = new Bundle();

        scoresheet1 = findViewById(R.id.scoresheet1);
        scoresheet2 = findViewById(R.id.scoresheet2);
        scoresheet3 = findViewById(R.id.scoresheet3);
        scoresheet4 = findViewById(R.id.scoresheet4);
        scoresheet5 = findViewById(R.id.scoresheet5);

        delete = findViewById(R.id.delete);
        open = findViewById(R.id.open);
        add = findViewById(R.id.add);

        allButtons = new ArrayList();
        allButtons.add(scoresheet1);
        allButtons.add(scoresheet2);
        allButtons.add(scoresheet3);
        allButtons.add(scoresheet4);
        allButtons.add(scoresheet5);

        currentButton = scoresheet1;

        path = getApplicationContext().getFilesDir();
        dir = new File(String.valueOf(path));

        allFiles = path.listFiles();

        getAllFiles(allFiles);

        buttonSetup(allFiles, allButtons);

        scoresheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentButton.setBackgroundResource(R.color.colorButton);
                scoresheet1.setBackgroundResource(R.color.colorButtonChange);
                currentButton = scoresheet1;
            }
        });

        scoresheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentButton.setBackgroundResource(R.color.colorButton);
                scoresheet2.setBackgroundResource(R.color.colorButtonChange);
                currentButton = scoresheet2;
            }
        });

        scoresheet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentButton.setBackgroundResource(R.color.colorButton);
                scoresheet3.setBackgroundResource(R.color.colorButtonChange);
                currentButton = scoresheet3;
            }
        });

        scoresheet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentButton.setBackgroundResource(R.color.colorButton);
                scoresheet4.setBackgroundResource(R.color.colorButtonChange);
                currentButton = scoresheet4;
            }
        });

        scoresheet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentButton.setBackgroundResource(R.color.colorButton);
                scoresheet5.setBackgroundResource(R.color.colorButtonChange);
                currentButton = scoresheet5;

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllFiles(allFiles);
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fileBundle.putString("File name", currentButton.getText() + ".txt");
                scoresheet.putExtras(fileBundle);
                startActivity(scoresheet);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Length", String.valueOf(allFiles.length));
                if (allFiles.length < 5) {
                    int index = allFiles.length;

                    file = new File(path, allButtons.get(index).getText() + ".txt");

                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    addButton(file, allButtons.get(index));
                    allFiles = path.listFiles();
                    getAllFiles(allFiles);

                }

            }
        });

    }
}
