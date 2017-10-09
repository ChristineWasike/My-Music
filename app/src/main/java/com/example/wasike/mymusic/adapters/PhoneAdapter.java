package com.example.wasike.mymusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.model.Phone;
import com.example.wasike.mymusic.ui.MainActivity;
import com.example.wasike.mymusic.ui.PhoneStore;
import com.example.wasike.mymusic.ui.SoundDemo;

import java.io.File;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by wasike on 04/10/17.
 */

public class PhoneAdapter extends BaseAdapter {
    private ArrayList<Phone> phoneSongs;
    private LayoutInflater songInf;
    private  Phone mPhone;
    private Context mContext;

    public PhoneAdapter(Context c, ArrayList<Phone> theSongs) {
        mContext = c;
        phoneSongs = theSongs;
        songInf = LayoutInflater.from(c);
    }


    @Override
    public int getCount() {
        return phoneSongs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //map to song layout
        LinearLayout songLay = (LinearLayout)songInf.inflate(R.layout.song, parent, false);
        //get title and artist views
        TextView songView = (TextView)songLay.findViewById(R.id.song_title);
        TextView artistView = (TextView)songLay.findViewById(R.id.song_artist);
        //get song using position
        Phone currSong = phoneSongs.get(position);
        //get title and artist strings
        songView.setText(currSong.getphoneTitle());
        artistView.setText(currSong.getphoneArtist());
        //set position as tag
        songLay.setTag(position);

        songLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SoundDemo.class);
                mContext.startActivity(intent);
            }
        });



        return songLay;
    }
}
