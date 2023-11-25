package com.example.audicoastoverdrivedirector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Music extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FileAdapter fileAdapter;
    private List<File> fileList;
    private ImageView img_back;
    private TextView tv_pathHolder;
    File storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music2);

        tv_pathHolder = findViewById(R.id.tv_pathHolder);
        img_back = findViewById(R.id.img_back);

        //storage......


        String internalStorage = System.getenv("EXTERNAL_STORAGE");
        storage = new File(internalStorage);

        //tv_pathHolder.setText(storage.getAbsolutePath());
        runtimePermission();
    }

    public void runtimePermission() {
        Dexter.withContext(Music.this).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                displayFiles();

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                permissionToken.continuePermissionRequest();

            }
        }).check();

    }

    public ArrayList<File> findFiles(File file)
    {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();


        for(File singleFile: files)
        {
            if( singleFile.getName().toLowerCase().endsWith(".mp3") || singleFile.getName().toLowerCase().endsWith(".wav") )
            {
                arrayList.add(singleFile);
            }
        }

        return  arrayList;

    }

    public void displayFiles() {

        recyclerView = findViewById(R.id.recycler_internal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(Music.this,1));
        fileList = new ArrayList<>();
        fileList.addAll(findFiles(storage));

        fileAdapter = new FileAdapter(Music.this,fileList);
        recyclerView.setAdapter(fileAdapter);
    }


}