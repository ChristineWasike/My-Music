package com.example.wasike.mymusic;

import android.util.Log;

import com.example.wasike.mymusic.BuildConfig;
import com.example.wasike.mymusic.model.Song;

/**
 * Created by wasike on 16/09/17.
 */

public class Constants {
    public static final String MUSIC_API_KEY = BuildConfig.MUSIC_API_KEY;
    public static final String MUSIC_BASE_URL = "https://api.genius.com/search?";
    public static final String MUSIC_QUERY_PARAMETER = "q";
    public static final String SONG_BASE_URL = "https://api.genius.com/";
    public static final String PREFERENCE_SONG_KEY = "song";
    public static final String FIREBASE_CHILD_SEARCHED_SONG = "search";
    public static final String FIREBASE_CHILD_SONGS = "songs";
    public static final String FIREBASE_QUERY_INDEX = "index";
}
