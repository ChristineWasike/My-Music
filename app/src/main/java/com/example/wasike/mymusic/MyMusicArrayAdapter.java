package com.example.wasike.mymusic;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by wasike on 11/09/17.
 */

public class MyMusicArrayAdapter extends ArrayAdapter{
    private Context mContext;
    private String[] mLyrics;
    private String[] mCuisines;

    public MyMusicArrayAdapter(Context mContext, int resource, String[] mLyrics, String[] mCuisines) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mLyrics = mLyrics;
        this.mCuisines = mCuisines;
    }

    @Override
    public Object getItem(int position) {
        String lyric = mLyrics[position];
        String cuisine = mCuisines[position];
        return String.format("%s \nServes great: %s", lyric, cuisine);
    }

    @Override
    public int getCount() {
        return mLyrics.length;
    }

}
