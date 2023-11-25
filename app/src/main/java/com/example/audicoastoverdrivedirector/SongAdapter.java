package com.example.audicoastoverdrivedirector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {
    public SongAdapter(@NonNull  Context context, int resource, @NonNull  List<Song> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song,null);
        TextView tvTitle =convertView.findViewById(R.id.tvTitle);
        TextView tvArtist =convertView.findViewById(R.id.tvArtist);
        Song song = getItem(position);
        tvTitle.setText(song.getTitle());
        tvArtist.setText(song.getArtist());
        return convertView;

    }
}
