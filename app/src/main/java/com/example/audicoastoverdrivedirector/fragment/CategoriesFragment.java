package com.example.audicoastoverdrivedirector.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.audicoastoverdrivedirector.Folder;
import com.example.audicoastoverdrivedirector.Image;
import com.example.audicoastoverdrivedirector.Music;
import com.example.audicoastoverdrivedirector.R;
import com.example.audicoastoverdrivedirector.Video;

public class CategoriesFragment extends Fragment implements View.OnClickListener {



    View view;
    public CardView card1, card2, card3, card4;
    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_categories,container,false);

        card1 = (CardView)view.findViewById(R.id.cv1);
        card2 = (CardView)view.findViewById(R.id.cv2);
        card3 = (CardView)view.findViewById(R.id.cv3);
        card4 = (CardView)view.findViewById(R.id.cv4);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);



        return view;
    }
    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId())
        {
            case R.id.cv1 :
                i = new Intent(v.getContext(), Folder.class);
                startActivity(i);
                break;

            case R.id.cv2 :
                i = new Intent(v.getContext(), Image.class);
                startActivity(i);
                break;
            case R.id.cv3 :
                i = new Intent(v.getContext(), Music.class);
                startActivity(i);
                break;
            case R.id.cv4 :
                i = new Intent(v.getContext(), Video.class);
                startActivity(i);
                break;


        }

    }
}
