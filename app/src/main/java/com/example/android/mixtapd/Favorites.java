package com.example.android.mixtapd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_contents);
        setupToolbar();
        setFavoritesContents();
        goFullscreen();
    }

    private void goFullscreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setupToolbar() {
        Toolbar mActionToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(R.string.favorites);
    }

    private void setFavoritesContents() {
        ArrayList<Favorite> arrayOfFavorites = new ArrayList<Favorite>();
        FavoritesAdapter adapter = new FavoritesAdapter(this, arrayOfFavorites);
        adapter.add(new Favorite(getString(R.string.mixtape01_name)));
        adapter.add(new Favorite(getString(R.string.mixtape03_name)));

        ListView listView = (ListView) findViewById(R.id.playlist_contents_list);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mainActivityIntent = new Intent(Favorites.this, MainActivity.class);
        startActivity(mainActivityIntent);
        return true;
    }

}