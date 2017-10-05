package com.example.wasike.mymusic.model;

import static android.R.attr.id;

/**
 * Created by wasike on 04/10/17.
 */

public class Phone {
    private long phoneId;
    private String phoneTitle;
    private String phoneArtist;

    public Phone(long songID, String songTitle, String songArtist) {
        phoneId=songID;
        phoneTitle=songTitle;
        phoneArtist=songArtist;
    }

    public long getphoneID(){
        return phoneId;
    }

    public String getphoneTitle(){
        return phoneTitle;
    }

    public String getphoneArtist(){
        return phoneArtist;
    }
}
