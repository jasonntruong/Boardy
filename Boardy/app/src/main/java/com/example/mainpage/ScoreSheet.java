package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class ScoreSheet extends AppCompatActivity {

    FileOutputStream outStream;
    FileInputStream inStream;
    File file;

    EditText writeBox;
    Button sendToFile;
    TextView previousText;

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

    protected void writeFile(File file, String message){
        try {
            outStream = new FileOutputStream(file);
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

        writeBox = findViewById(R.id.writeText);
        sendToFile = findViewById(R.id.sendToFile);
        previousText = findViewById(R.id.previousText);

        File path = getApplicationContext().getFilesDir();
        File dir = new File(String.valueOf(path));
        File[] allFiles = dir.listFiles();

        Log.d("File", String.valueOf(path));

        file = new File(path, "score-sheet2.txt");

        getAllFiles(allFiles);

        previousText.setText("Previous Text: " + readFile(file));

        sendToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile(file, String.valueOf(writeBox.getText()));
                previousText.setText("Previous Text: " + readFile(file));

                Log.d("File contents:", readFile(file));
            }
        });

    }
}
