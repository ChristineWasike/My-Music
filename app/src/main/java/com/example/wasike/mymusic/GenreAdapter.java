package com.example.wasike.mymusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by wasike on 11/09/17.
 */

public class GenreAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mGenres;
    public GenreAdapter (Context context, String[] genres){
        this.mContext = context;
        this.mGenres = genres;
    }


    @Override
    public int getCount() {
        return mGenres.length;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            // get layout from xml file
            gridView = inflater.inflate(R.layout.genre_grid_item, null);
            // pull views
            TextView letterView = (TextView) gridView
                    .findViewById(R.id.grid_item_letter);
            // set values into views
            letterView.setText("Genre");  // using dummy data for now
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}

