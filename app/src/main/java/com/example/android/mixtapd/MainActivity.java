package com.example.android.mixtapd;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMixtapes();
        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar mActionToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setTitle("");
    }

    private void setupMixtapes() {
        final ImageView mixtape01 = (ImageView) findViewById(R.id.mixtape01);
        final ImageView mixtape02 = (ImageView) findViewById(R.id.mixtape02);
        final ImageView mixtape03 = (ImageView) findViewById(R.id.mixtape03);
        final ImageView mixtape04 = (ImageView) findViewById(R.id.mixtape04);
        Glide.with(this).load(R.drawable.mixtape01).centerCrop().into(mixtape01);
        Glide.with(this).load(R.drawable.mixtape02).centerCrop().into(mixtape02);
        Glide.with(this).load(R.drawable.mixtape03).centerCrop().into(mixtape03);
        Glide.with(this).load(R.drawable.mixtape04).centerCrop().into(mixtape04);

        View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                addToFavorites(v);
                return true;
            }
        };
        mixtape01.setOnLongClickListener(longClickListener);
        mixtape02.setOnLongClickListener(longClickListener);
        mixtape03.setOnLongClickListener(longClickListener);
        mixtape04.setOnLongClickListener(longClickListener);

        mixtape01.setTag("mixtape01");
        mixtape02.setTag("mixtape02");
        mixtape03.setTag("mixtape03");
        mixtape04.setTag("mixtape04");

        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent nowPlayingIntent = new Intent(MainActivity.this, NowPlaying.class);
                nowPlayingIntent.putExtra("background", (String) v.getTag());
                nowPlayingIntent.putExtra("name", (String) v.getContentDescription());
                startActivity(nowPlayingIntent);
            }
        };

        mixtape01.setOnClickListener(clickListener);
        mixtape02.setOnClickListener(clickListener);
        mixtape03.setOnClickListener(clickListener);
        mixtape04.setOnClickListener(clickListener);
    }

    public void addToFavorites(View v) {
        String str = getString(R.string.added_to_favorites);
        Snackbar.make(findViewById(R.id.frameLayout), str, Snackbar.LENGTH_LONG).show();
    }

    public void showFavorites() {
        Snackbar.make(findViewById(R.id.frameLayout), "This will show the favorites list", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showFavorites();
        return true;
    }

}
