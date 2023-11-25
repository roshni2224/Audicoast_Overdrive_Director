package com.example.audicoastoverdrivedirector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class CreateFolder extends AppCompatActivity  {

    private TextView folderName;
    private Button createBtn ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_folder);

        folderName = findViewById(R.id.tv_filepath);
        createBtn = findViewById(R.id.createButton);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);



        createBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                folderName.setText(createDirectory("DirPractise Directory","SubDirectory").getPath());
            }
        });
    }

    private  File createDirectory(String dirName, String subDir) {

        File file;
        if(subDir!= null){
            file = new File(getExternalFilesDir(null)+"/"+dirName+"/"+subDir);

        }else{

            file = new File(getExternalFilesDir(null)+"/"+dirName);

        }
        if(!file.exists()){
            file.mkdir();
        }

        return  file;
    }




    //---------------------------------
    /*
    public void createFile(View view){

        try {
            filepath.createNewFile();
            Toast.makeText(this, "File created successfully", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void deleteFile(View view){

        if(filepath.exists())
        {
            filepath.delete();
            Toast.makeText(this, "File Deleted Successfully", Toast.LENGTH_SHORT).show();
        }

    }

     */


}