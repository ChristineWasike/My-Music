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

import static android.R.attr.fragment;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.

 */
public class BlankFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.songImageView) ImageView mImageLabel;
    @Bind(R.id.songNameTextView) TextView mNameLabel;
    @Bind(R.id.TitleFTTextView) TextView mTitleFTLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;

    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.FTArtistTextView) TextView mFTArtistLabel;
//    @Bind(R.id.);
    @Bind(R.id.saveSongButton) TextView mSaveSongButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View view) {

    }
}
