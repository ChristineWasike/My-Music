package com.example.wasike.mymusic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.adapters.SongListAdapter;
import com.example.wasike.mymusic.model.Song;
import com.example.wasike.mymusic.services.SongService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MTActivity extends AppCompatActivity {
    public static final String TAG = MTActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) public RecyclerView mRecyclerView;
//    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.titleTextView) TextView mTitleTextView;

    private SongListAdapter mAdapter;
    public ArrayList<Song> mSongs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        mTitleTextView.setText("The lyrics to your song: " + title);

        getTitles(title);
    }

    private void getTitles(String title) {
        final SongService songService = new SongService();

        songService.findSong(title, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mSongs = songService.processResults(response);

                MTActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new SongListAdapter(getApplicationContext(),mSongs);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(MTActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
