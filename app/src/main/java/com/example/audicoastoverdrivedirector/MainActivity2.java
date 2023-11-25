package com.example.audicoastoverdrivedirector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    View view;

    ImageView ImageFile, ImageMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageFile = findViewById(R.id.overdrive_image_file);
        ImageMusic = findViewById(R.id.audicoast_image);

        ImageFile.setOnClickListener((View.OnClickListener) this);
        ImageMusic.setOnClickListener((View.OnClickListener) this);


    }
    public void onClick(View v) {
        Intent i;

        switch (v.getId())
        {
            case R.id.overdrive_image_file:
                i = new Intent(MainActivity2.this, FileActivity.class);
                Toast.makeText(getApplicationContext(),"Clicked Overdrive Director",Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;

            case R.id.audicoast_image:
                i = new Intent(MainActivity2.this, MusicActivity.class);
                Toast.makeText(getApplicationContext(),"Clicked AudiCoast",Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;



        }
    }
}