package com.example.wasike.mymusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wasike on 08/09/17.
 */

public class LyricsActivity extends AppCompatActivity {
    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.titleTextView) TextView mTitleTextView;

    private String[] lyrics = new String[] {
        "Radiohead", "The King of Limbs", "Bloom", "Morning Mr Magpie",
                "Little by Little","Feral", "Lotus Flower", "Codex",
                "Give Up the Ghost", "Separator"};

    private String[] cuisines = new String[] {"Vegan Food", "Breakfast",
            "Fishs Dishs", "Scandinavian", "Coffee", "English Food",
            "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ",
            "Cuban", "Bar Food", "Sports Bar", "Breakfast", "Mexican" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        ButterKnife.bind(this);


        MyMusicArrayAdapter adapter = new MyMusicArrayAdapter(this, android.R.layout.simple_list_item_1, lyrics, cuisines);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String lyric = ((TextView)view).getText().toString();
                Toast.makeText(LyricsActivity.this, lyric, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

//        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
//        tabs.addTab(tabs.newTab().setText("Tab 1"));
//        tabs.addTab(tabs.newTab().setText("Tab 2"));
//        tabs.addTab(tabs.newTab().setText("Tab 3"));

        mTitleTextView.setText("The lyrics to your song: " + title);
    }
}

