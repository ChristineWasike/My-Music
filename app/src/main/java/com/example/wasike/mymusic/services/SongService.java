package com.example.wasike.mymusic.services;


import android.util.Log;

import com.example.wasike.mymusic.Constants;
import com.example.wasike.mymusic.model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by wasike on 16/09/17.
 */

public class SongService {
    public static void findSong(String song, Callback callback) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MUSIC_BASE_URL).newBuilder();

        urlBuilder.addQueryParameter(Constants.MUSIC_QUERY_PARAMETER, song);


        String url =urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient();

        Request request= new Request.Builder()
            .header("Authorization", Constants.MUSIC_API_KEY)
            .url(url)
            .build();
        Log.v("Information display", url);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Song> processResults(Response response){
        ArrayList<Song> songs = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject geniusJson = new JSONObject(jsonData);
                JSONObject responseJson = geniusJson.getJSONObject("response");
                Log.v("jsonData", jsonData);

                JSONArray songJsonObject = responseJson.getJSONArray("hits");
                for (int i = 0; i < songJsonObject.length(); i++) {
                    JSONObject hitJson = songJsonObject.getJSONObject(i);
                    String type= hitJson.getString("type");
                    Log.v("type", type);
                    String title = hitJson.getJSONObject("result").getString("title");
                    String titleWithFeatured = hitJson.getJSONObject("result").getString("title_with_featured");
                    Log.v("titleWithFeatured", titleWithFeatured);
                    String url = hitJson.getJSONObject("result").getString("url");
                    Log.v("url", url);
                    String fulltitle = hitJson.getJSONObject("result").getString("full_title");
                    String headerImageUrl = hitJson.getJSONObject("result").getString("header_image_url");
                    String artistName = hitJson.getJSONObject("result").getJSONObject("primary_artist").getString("name");

                    Song song = new Song(type,title, titleWithFeatured,url, fulltitle,headerImageUrl,artistName );
                    songs.add(song);


                }

            }

        }catch(IOException e){
            e.printStackTrace();
        } catch(JSONException e){
            e.printStackTrace();
        }
        return songs;

    }
}
