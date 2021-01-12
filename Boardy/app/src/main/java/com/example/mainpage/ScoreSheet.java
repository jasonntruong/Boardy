package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ScoreSheet extends AppCompatActivity {
    Intent mainActivity;

    FileOutputStream outStream;
    FileInputStream inStream;
    File file, path, dir;
    File[] allFiles;

    List<EditText> allNames, allPoints;

    EditText scoreboardTitle, name, points;
    Button save, clear;

    protected void getAllFiles(File[] allFiles){
        for (int i = 0; i < allFiles.length; i++){
            Log.d("File name", allFiles[i].getName());
        }

    }

    protected void deleteAllFiles(File[] allFiles){
        for (int i = 0; i < allFiles.length; i++){
            allFiles[i].delete();
            Log.d("Deleted", allFiles[i].getName());
        }
    }

    protected void writeFile(File file, String message, Boolean append){
        try {
            outStream = new FileOutputStream(file, append);
            outStream.write(message.getBytes());
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String readFile(File file){
        byte[] bytes = new byte[(int) file.length()];

        try {
            inStream = new FileInputStream(file);
            inStream.read(bytes);
            inStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(bytes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_sheet);

        mainActivity = new Intent(getApplicationContext(), MainActivity.class);

        save = findViewById(R.id.save);
        clear = findViewById(R.id.clear);
        scoreboardTitle = findViewById(R.id.scoreboardTitle);

        name = findViewById(R.id.name1);
        points = findViewById(R.id.points1);

        path = getApplicationContext().getFilesDir();
        dir = new File(String.valueOf(path));
        allFiles = dir.listFiles();

        allNames = new ArrayList();
        allPoints = new ArrayList();

        allNames.add((EditText) findViewById(R.id.name0));
        allNames.add((EditText) findViewById(R.id.name1));
        allNames.add((EditText) findViewById(R.id.name2));
        allNames.add((EditText) findViewById(R.id.name3));
        allNames.add((EditText) findViewById(R.id.name4));
        allNames.add((EditText) findViewById(R.id.name5));
        allNames.add((EditText) findViewById(R.id.name6));
        allNames.add((EditText) findViewById(R.id.name7));
        allNames.add((EditText) findViewById(R.id.name8));

        allPoints.add((EditText) findViewById(R.id.points0));
        allPoints.add((EditText) findViewById(R.id.points1));
        allPoints.add((EditText) findViewById(R.id.points2));
        allPoints.add((EditText) findViewById(R.id.points3));
        allPoints.add((EditText) findViewById(R.id.points4));
        allPoints.add((EditText) findViewById(R.id.points5));
        allPoints.add((EditText) findViewById(R.id.points6));
        allPoints.add((EditText) findViewById(R.id.points7));
        allPoints.add((EditText) findViewById(R.id.points8));

        Log.d("File", String.valueOf(path));

        if (allFiles.length >= 1){
            file = allFiles[0];
            String content = readFile(file);
            Log.d("Initial", content);
            String[] contentList = content.split("\\.");
            Log.d("Length", String.valueOf(contentList.length));

            try {
                for (int i = 0; i < allNames.size(); i++) {
                    String[] innerContent = String.valueOf(contentList[i]).split(",");
                    if (innerContent.length == 2) {
                        allNames.get(i).setText(innerContent[0]);
                        allPoints.get(i).setText(innerContent[1]);
                        Log.d("Content", String.valueOf(innerContent[0]));
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                name.setText("");
                points.setText("");
            }
        }

        else{
            file = new File(path, "score-sheet.txt");
        }

        String fileName = file.getName().replace(".txt", "");

        scoreboardTitle.setText(fileName);

        getAllFiles(allFiles);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile(file, "", false);
                for (int i = 0; i < allNames.size(); i++){
                    String currentName = String.valueOf(allNames.get(i).getText()).replace(",", "").replace(".", "");
                    String currentPoints = String.valueOf(allPoints.get(i).getText()).replace(",", "").replace(".", "");
                    if (currentName.equals("") || currentPoints.equals("")){
                        writeFile(file, ",.", true);
                    }
                    else{
                        writeFile(file, currentName + "," + currentPoints + ".", true);
                    }

                }

                Log.d("File Content", readFile(file));


                file.renameTo(new File(path,scoreboardTitle.getText() + ".txt"));

                startActivity(mainActivity);

            }
        });

        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                for (int i = 0; i < allNames.size(); i++){
                    allNames.get(i).setText("");
                    allPoints.get(i).setText("");
                }
                return false;
            }
        });

    }
}