package com.example.wasike.mymusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wasike.mymusic.Constants;
import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.model.Song;
import com.example.wasike.mymusic.ui.SongDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.wasike.mymusic.R.id.songImageView;

/**
 * Created by wasike on 19/09/17.
 */

public class FirebaseSongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
private static final int MAX_WIDTH = 200;
private static final int MAX_HEIGHT = 200;
public ImageView mSongImageView;


    View mView;
    Context mContext;

    public FirebaseSongViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindSong(Song song) {
        mSongImageView = (ImageView) mView.findViewById(R.id.songImageView);
//        ImageView songImageView = (ImageView) mView.findViewById(songImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.songNameTextView);
        TextView artistTextView = (TextView) mView.findViewById(R.id.typeTextView);

        Picasso.with(mContext)
                .load(song.getHeaderImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mSongImageView);

        nameTextView.setText(song.getTitle());
        artistTextView.setText(song.getArtistName());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Song> songs = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SONGS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    songs.add(snapshot.getValue(Song.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, SongDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("songs", Parcels.wrap(songs));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
