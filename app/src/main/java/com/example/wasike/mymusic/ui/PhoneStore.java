package com.example.wasike.mymusic.ui;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.adapters.PhoneAdapter;
import com.example.wasike.mymusic.model.Phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhoneStore extends AppCompatActivity {
    private ArrayList<Phone> phoneList;
    @Bind(R.id.song_list) ListView songView;
    Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_store);
        ButterKnife.bind(this);

        //songView = (ListView) findViewById(R.id.song_list);

        phoneList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant

                return;
            }
        }

        getSongList();

        //sorting songs alphabetically
        Collections.sort(phoneList, new Comparator<Phone>(){
            public int compare(Phone a, Phone b){
                return a.getphoneTitle().compareTo(b.getphoneTitle());
            }
        });

        PhoneAdapter phoneAdt = new PhoneAdapter(this, phoneList);
        songView.setAdapter(phoneAdt);

    }

    //method responsible for retrieving audio file information from device
    public void getSongList() {
        //retrieve song info
        //created a ContentResolver instance
        // retrieved the URI for external music files
        // created a Cursor instance using the ContentResolver instance to query the music files
        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);


        //Iterate over results to check for valid data

        if (musicCursor!= null && musicCursor.moveToFirst()) {
            //get columns
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int audioColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            //add songs to list
            do {
                //creating a new song object
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                phoneList.add(new Phone(thisId,thisTitle,thisArtist));
            }

            while (musicCursor.moveToNext());


        }

    }
}
