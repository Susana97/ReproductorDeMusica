package com.example.susanasantosmoreno.reproductordemusica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Songs> {

    ArrayList<Songs> songsArray;

    public SongAdapter(Context context, ArrayList<Songs> songsArray) {
        super(context, R.layout.songs_array, songsArray);
        this.songsArray = songsArray;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.songs_array, null);

        TextView textViewTitle = (TextView)item.findViewById(R.id.songTitle);
        textViewTitle.setText(songsArray.get(position).getSongTitle());

        TextView textViewArtist = (TextView)item.findViewById(R.id.songArtist);
        textViewArtist.setText(songsArray.get(position).getSongArtist());

        ImageView imageViewCover = (ImageView)item.findViewById(R.id.songImage);
        imageViewCover.setImageResource(R.drawable.reproductor_de_musica);

        return(item);
    }
}
