package com.example.android.mixtapd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class NowPlaying extends AppCompatActivity {

    String background;
    String mixtapeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        final ImageView nowPlayingBackground = (ImageView) findViewById(R.id.now_playing_background);
        Context context = nowPlayingBackground.getContext();
        Intent intent = getIntent();
        background = intent.getStringExtra("background");
        mixtapeName = intent.getStringExtra("name");
        setupBackgroundImage(context, nowPlayingBackground);
        setupToolbar(mixtapeName);
    }

    private void setupBackgroundImage(Context context, ImageView nowPlayingBackground) {
        int nowPlayingBackgroundId = context.getResources().getIdentifier(background, "drawable", context.getPackageName());
        Glide.with(this).load(nowPlayingBackgroundId).centerCrop().into(nowPlayingBackground);
    }

    private void setupToolbar(String title) {
        Toolbar mActionToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    private void showPlaylistContents() {
        Intent playlistContentsIntent = new Intent(NowPlaying.this, PlaylistContents.class);
        playlistContentsIntent.putExtra("background", background);
        playlistContentsIntent.putExtra("name", mixtapeName);
        startActivity(playlistContentsIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.now_playing_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v("Tentacle",""+item.getItemId());
        Log.v("Tentacle","Home Is"+android.R.id.home);

        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(NowPlaying.this, MainActivity.class));
                return true;
            default:
                showPlaylistContents();
                return true;
        }

    }


}
