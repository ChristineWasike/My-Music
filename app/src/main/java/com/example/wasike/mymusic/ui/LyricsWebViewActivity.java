package com.example.wasike.mymusic.ui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.wasike.mymusic.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LyricsWebViewActivity extends AppCompatActivity {
    @Bind(R.id.lyricsWebView) WebView lyricsWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        setContentView(webView);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
    }

}
