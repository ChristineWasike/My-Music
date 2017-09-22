package com.example.wasike.mymusic.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.app.FragmentPagerAdapter;

import com.example.wasike.mymusic.model.Song;
import com.example.wasike.mymusic.ui.BlankFragment;

import java.util.ArrayList;

/**
// * Created by wasike on 18/09/17.
// */

public class SongPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Song> mSongs;

    public SongPagerAdapter(FragmentManager fm, ArrayList<Song> songs){
        super(fm);
        mSongs = songs;
    }

    @Override
    public Fragment getItem(int position) {
        return BlankFragment.newInstance(mSongs.get(position));
    }

    @Override
    public int getCount() {
        return mSongs.size();
    }
}
