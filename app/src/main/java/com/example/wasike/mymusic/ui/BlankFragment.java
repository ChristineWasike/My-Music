package com.example.wasike.mymusic.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.model.Song;
import com.skyfishjy.library.RippleBackground;
import com.squareup.picasso.Picasso;

import static android.R.attr.fragment;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.

 */
 public class BlankFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    //@Bind(R.id.content) RippleBackground mRippleBackground;




    @Bind(R.id.songImageView) ImageView mImageLabel;
    @Bind(R.id.songNameTextView) TextView mNameLabel;
//    @Bind(R.id.TitleFTTextView) TextView mTitleFTLabel;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
       // final RippleBackground rippleBackground = (RippleBackground) mRippleBackground;
        super.onCreate(savedInstanceState);
        mSong = Parcels.unwrap(getArguments().getParcelable("song"));
       // rippleBackground.startRippleAnimation();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mSong.getArtistImage()).resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop().into(mImageLabel);

        mNameLabel.setText(mSong.getTitle());
        mArtistLabel.setText(mSong.getArtistName());
        mFTArtistLabel.setText(mSong.getTitleWithFeatured());

        mSaveSongButton.setOnClickListener(this);

        mWebsiteLabel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //rippleBackground.startRippleAnimation();
                Intent intent = new Intent(view.getContext(), LyricsWebViewActivity.class);
                intent.putExtra("url", mSong.getUrl());
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
