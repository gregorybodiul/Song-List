package com.example.songlist;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Playlist {
    private ArrayList<Drawable> playlistCover = new ArrayList<>();
    private ArrayList<String> playlistArtistName = new ArrayList<>();
    private ArrayList<String> playlistSongName = new ArrayList<>();
    private static Playlist instance;

    Playlist(){
        this.instance = this;
    }
    public static Playlist getInstance(){
        return instance;
    }
    public void addSong(Drawable drawable, String artistName, String songName){
        playlistCover.add(drawable);
        playlistArtistName.add(artistName);
        playlistSongName.add(songName);
    }
    public Drawable getCover(int pos) {
        return playlistCover.get(pos);
    }
    public String getArtistName(int pos){
        return playlistArtistName.get(pos);
    }
    public String getSongName(int pos){
        return playlistSongName.get(pos);
    }
    public int getCountSong(){
        return playlistSongName.size();
    }
    public void loadDataForTest(Resources resources){
        addSong(resources.getDrawable(R.drawable.cover1), "Artist 1", resources.getResourceEntryName(R.raw.audio_1));
        addSong(resources.getDrawable(R.drawable.cover2), "Artist 2", resources.getResourceEntryName(R.raw.audio_2));
        addSong(resources.getDrawable(R.drawable.cover3), "Artist 3", resources.getResourceEntryName(R.raw.audio_3));
        addSong(resources.getDrawable(R.drawable.cover4), "Artist 4", resources.getResourceEntryName(R.raw.audio_4));
        addSong(resources.getDrawable(R.drawable.cover5), "Artist 5", resources.getResourceEntryName(R.raw.audio_5));
        addSong(resources.getDrawable(R.drawable.cover6), "Artist 6", resources.getResourceEntryName(R.raw.audio_6));
        addSong(resources.getDrawable(R.drawable.cover7), "Artist 7", resources.getResourceEntryName(R.raw.audio_7));
        addSong(resources.getDrawable(R.drawable.cover8), "Artist 8", resources.getResourceEntryName(R.raw.audio_8));
        addSong(resources.getDrawable(R.drawable.cover9), "Artist 9", resources.getResourceEntryName(R.raw.audio_9));
        addSong(resources.getDrawable(R.drawable.cover10), "Artist 10", resources.getResourceEntryName(R.raw.audio_10));
        addSong(resources.getDrawable(R.drawable.cover11), "Artist 11", resources.getResourceEntryName(R.raw.audio_11));
        addSong(resources.getDrawable(R.drawable.cover12), "Artist 12", resources.getResourceEntryName(R.raw.audio_12));

    }
}
