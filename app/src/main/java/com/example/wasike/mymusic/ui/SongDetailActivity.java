package com.example.wasike.mymusic.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.RotateAnimation;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.adapters.SongPagerAdapter;
import com.example.wasike.mymusic.model.Song;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SongDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private SongPagerAdapter adapterViewPager;
    ArrayList<Song> mSongs = new ArrayList<>();
    private Song mSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        ButterKnife.bind(this);

        mSongs = Parcels.unwrap(getIntent().getParcelableExtra("songs"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new SongPagerAdapter(getSupportFragmentManager(), mSongs);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setPageTransformer(true, new CubeOutTransformer());
        mViewPager.setCurrentItem(startingPosition);

        getSupportActionBar().setTitle(mSongs.get(startingPosition).getTitle());

    }
}
