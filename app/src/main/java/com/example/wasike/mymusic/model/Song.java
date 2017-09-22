package com.example.wasike.mymusic.model;

import org.parceler.Parcel;

/**
 * Created by wasike on 16/09/17.
 */

@Parcel
public class Song {
    private String mType;
    private String mTitle;
    private String mUrl;
    private String mTitleWithFeatured;
    private String mFulltitle;
    private String mHeaderImageUrl;
    private String mArtistName;
    private String mArtistImage;


    public Song(){}
    public Song(String type, String title, String titleWithFeatured,
                String url, String fulltitle, String headerImageUrl, String artistName, String artistImage ){
        mType= type;
        mTitle = title;
        mTitleWithFeatured = titleWithFeatured;
        mUrl = url;
        mFulltitle = fulltitle;
        mHeaderImageUrl = headerImageUrl;
        mArtistName = artistName;
        mArtistImage = artistImage;
    }

    public String getType() {
        return mType;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTitleWithFeatured() {
        return mTitleWithFeatured;
    }


    public String getFulltitle() {
        return mFulltitle;
    }

    public String getHeaderImageUrl() {
        return mHeaderImageUrl;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getArtistImage() {
        return mArtistImage;
    }
}
