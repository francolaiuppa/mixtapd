package com.example.android.mixtapd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlaylistContents extends AppCompatActivity {

    String background;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_contents);
        background = getIntent().getStringExtra("background");
        name = getIntent().getStringExtra("name");
        setupBackgroundImage();
        setupToolbar();
        setPlaylistContents();
        goFullscreen();
    }

    private void goFullscreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setupBackgroundImage() {
        final ImageView playlistContentsBackground = (ImageView) findViewById(R.id.playlist_contents_background);
        Context context = playlistContentsBackground.getContext();
        int playlistContentsBackgroundId = context.getResources().getIdentifier(background, "drawable", context.getPackageName());
        Glide.with(this).load(playlistContentsBackgroundId).centerCrop().into(playlistContentsBackground);
        playlistContentsBackground.setImageAlpha(40);
    }

    private void setupToolbar() {
        Toolbar mActionToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(getString(R.string.playlist_contents_title, name));
    }


    private void setPlaylistContents() {
        ArrayList<Song> arrayOfSongs = new ArrayList<Song>();
        SongsAdapter adapter = new SongsAdapter(this, arrayOfSongs);
        // TODO this could be read from an XML file rather than hardcoded in the Java code
        // alternatively it could come from a remote JSON API
        adapter.add(new Song("Wax Tailor", "Seize the day", false));
        adapter.add(new Song("Guts", "I want you tonight", true));
        adapter.add(new Song("P.E.O", "Be My Woman", false));
        adapter.add(new Song("Alan Braxe", "Palladium", false));
        adapter.add(new Song("Red Astaire", "Love to Angie", false));
        adapter.add(new Song("Calvin Harris & Disciples", "How deep is your love", false));

        ListView listView = (ListView) findViewById(R.id.playlist_contents_list);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.playlist_contents, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent nowPlayingIntent = new Intent(PlaylistContents.this, NowPlaying.class);
        nowPlayingIntent.putExtra("background", background);
        nowPlayingIntent.putExtra("name", name);
        startActivity(nowPlayingIntent);
        return true;
    }

}