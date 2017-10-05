package com.example.wasike.mymusic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wasike.mymusic.Constants;
import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.model.Song;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import org.parceler.Parcels;
import butterknife.Bind;
import butterknife.ButterKnife;


 public class BlankFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 450;
    private static final int MAX_HEIGHT = 350;


    @Bind(R.id.songImageView) ImageView mImageLabel;
    @Bind(R.id.songNameTextView) TextView mNameLabel;
    @Bind(R.id.artistTextView) TextView mArtistLabel;

    @Bind(R.id.centerImage) ImageView mWebsiteLabel;
    @Bind(R.id.FTArtistTextView) TextView mFTArtistLabel;

    @Bind(R.id.saveSongButton) TextView mSaveSongButton;

     private Song mSong;

    //created the fragment to display information on an individual song
    public  static  BlankFragment newInstance(Song song) {
      BlankFragment songDetailFragment = new BlankFragment();
      Bundle args = new Bundle();
      args.putParcelable("song", Parcels.wrap(song));
      songDetailFragment.setArguments(args);
      return songDetailFragment;
    }


    //unwrapping song object sent to this file initially in byte code
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSong = Parcels.unwrap(getArguments().getParcelable("song"));

    }

    //populating the song fragment with the data received on the song object
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mSong.getHeaderImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop().into(mImageLabel);

        mNameLabel.setText(mSong.getTitle());
        mArtistLabel.setText(mSong.getArtistName());
        mFTArtistLabel.setText(mSong.getTitleWithFeatured());

        mSaveSongButton.setOnClickListener(this);

        //setting an explicit intent to view the lyrics from within my application using the webview
        mWebsiteLabel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LyricsWebViewActivity.class);
                intent.putExtra("url", mSong.getUrl());
                startActivity(intent);
            }
        });


        return view;
    }


    //set to enable a user to save details on the particular song
    @Override
    public void onClick(View view) {
        if (view == mSaveSongButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference songReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_SONGS)
                    //created a node within the songs node
                    .child(uid);

            DatabaseReference pushRef = songReference.push();
            String pushId = pushRef.getKey();
            mSong.setPushId(pushId);
            pushRef.setValue(mSong);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

        }
    }
}
