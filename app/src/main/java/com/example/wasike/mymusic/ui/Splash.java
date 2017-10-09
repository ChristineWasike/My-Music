package com.example.wasike.mymusic.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wasike.mymusic.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {
    public static int time_out = 5000;
    @Bind(R.id.imageView) ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, time_out);

        getSupportActionBar().hide();
    }
}
