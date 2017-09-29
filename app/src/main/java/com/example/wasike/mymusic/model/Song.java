package com.example.wasike.mymusic.model;

import org.parceler.Parcel;

/**
 * Created by wasike on 16/09/17.
 */

@Parcel
public class Song {
    private String type;
    private String title;
    private String url;
    private String titleWithFeatured;
    private String fulltitle;
    private String headerImageUrl;
    private String artistName;
    private String artistImage;
    private String pushId;
    String index;


    public Song(){}
    public Song(String type, String title, String titleWithFeatured,
                String url, String fulltitle, String headerImageUrl, String artistName, String artistImage ){
        this.type= type;
        this.title = title;
        this.titleWithFeatured = titleWithFeatured;
        this.url = url;
        this.fulltitle = fulltitle;
        this.headerImageUrl = headerImageUrl;
        this.artistName = artistName;
        this.artistImage = artistImage;
        this.index = "not_specified";

    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitleWithFeatured() {
        return titleWithFeatured;
    }


    public String getFulltitle() {
        return fulltitle;
    }

    public String getHeaderImageUrl() {
        return headerImageUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistImage() {
        return artistImage;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
