package com.example.wasike.mymusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.model.Song;
import com.example.wasike.mymusic.ui.MTActivity;
import com.example.wasike.mymusic.ui.SongDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wasike on 16/09/17.
 */

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongViewHolder>{
    private ArrayList<Song> mSongs = new ArrayList<>();
    private Context mContext;
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    public SongListAdapter(Context context, ArrayList<Song> songs) {
        mContext = context;
        mSongs = songs;
    }

    @Override
    public SongListAdapter.SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item, parent, false);
        SongViewHolder viewHolder = new SongViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SongListAdapter.SongViewHolder holder, int position) {
        holder.bindSong(mSongs.get(position));
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.songImageView) ImageView msongImageView;
        @Bind(R.id.songNameTextView) TextView msongNameTextView;
        @Bind(R.id.songFTTextView) TextView msongFTTextView;
        @Bind(R.id.typeTextView)TextView mtypeTextView;

        private  Context mContext;
        public SongViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
         int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, SongDetailActivity.class);
            intent.putExtra("position", itemPosition);

            intent.putExtra("songs", Parcels.wrap(mSongs));
            mContext.startActivity(intent);
        }
        public void bindSong(Song song) {
            Picasso.with(mContext).load(song.getHeaderImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop().into(msongImageView);
            msongNameTextView.setText(song.getTitle());
            msongFTTextView.setText(song.getTitleWithFeatured());
            mtypeTextView.setText(song.getArtistName());
        }
    }
}
