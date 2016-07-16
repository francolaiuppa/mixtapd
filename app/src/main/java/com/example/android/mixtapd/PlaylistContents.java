package com.example.android.mixtapd;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

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
    }

    private void setupBackgroundImage() {
        final ImageView playlistContentsBackground = (ImageView) findViewById(R.id.playlist_contents_background);
        Context context = playlistContentsBackground.getContext();
        int playlistContentsBackgroundId = context.getResources().getIdentifier(background, "drawable", context.getPackageName());
        Glide.with(this).load(playlistContentsBackgroundId).centerCrop().into(playlistContentsBackground);
        playlistContentsBackground.setColorFilter(R.color.playlistContentsBackgroundTint, PorterDuff.Mode.XOR);
    }
    
    private void setupToolbar() {
        Toolbar mActionToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(name+" songs");
    }


    private void setPlaylistContents() {
        String[] items = new String[]{"Wax Tailor - Seize the day",
                "Dabeull, Holybrune - DX7",
                "Guts - I want you tonight",
                "Moon Boots - Love Strong",
                "P.E.O - Be My Woman",
                "Alan Braxe - Palladium",
                "Red Astaire - Love to Angie",
                "Calvin Harris & Disciples - How deep is your love"
        };

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        ListView listView = (ListView) findViewById(R.id.playlist_contents_list);
        listView.setAdapter(itemsAdapter);
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
