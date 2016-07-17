package com.example.android.mixtapd;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongsAdapter extends ArrayAdapter<Song> {
    public SongsAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Song song = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_song, parent, false);
        }
        // Lookup view for data population
        TextView tvArtist = (TextView) convertView.findViewById(R.id.tvArtist);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        ImageView tvNowPlaying = (ImageView) convertView.findViewById(R.id.tvNowPlaying);
        // Populate the data into the template view using the data object
        tvArtist.setText(song.artist);
        tvTitle.setText(song.title);
        if (song.isPlaying) {
            tvArtist.setTypeface(null, Typeface.BOLD);
            tvTitle.setTypeface(null, Typeface.BOLD);
            tvNowPlaying.setVisibility(View.VISIBLE);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}