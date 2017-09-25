package com.example.wasike.mymusic.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wasike.mymusic.Constants;
import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.adapters.SongListAdapter;
import com.example.wasike.mymusic.model.Song;
import com.example.wasike.mymusic.services.SongService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MTActivity extends AppCompatActivity {
    public static final String TAG = MTActivity.class.getSimpleName();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentSong;

    @Bind(R.id.recyclerView) public RecyclerView mRecyclerView;
//    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.progressBar) ProgressBar mProgressBar;


    private SongListAdapter mAdapter;
    public ArrayList<Song> mSongs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt);
        ButterKnife.bind(this);

//        Intent intent = getIntent();
//        String title = intent.getStringExtra(mRecentSong);


//        getTitles(title);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentSong = mSharedPreferences.getString(Constants.PREFERENCE_SONG_KEY, null);
        mTitleTextView.setText("The lyrics to your song: " + mRecentSong);
        Log.v(mRecentSong, "recent");


        if (mRecentSong != null) {
            getTitles(mRecentSong);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){


            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getTitles(query);
                Log.v(query, "query");
                mTitleTextView.setText("The lyrics to your song: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }

    private void addToSharedPreferences(String song){
        mEditor.putString(Constants.PREFERENCE_SONG_KEY, song).apply();

    }
}
