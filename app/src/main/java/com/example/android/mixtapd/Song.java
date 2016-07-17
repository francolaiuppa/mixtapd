package com.example.android.mixtapd;

public class Song {
    public String artist;
    public String title;
    public boolean isPlaying;

    public Song(String artist, String title, boolean isPlaying) {
        this.artist = artist;
        this.title = title;
        this.isPlaying = isPlaying;
    }
}