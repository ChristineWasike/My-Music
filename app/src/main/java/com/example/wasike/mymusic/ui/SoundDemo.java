package com.example.wasike.mymusic.ui;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.example.wasike.mymusic.R;

public class SoundDemo extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    //allows for working with audio system of device
    AudioManager audioManager;

    public void playAudio(View view){

        mediaPlayer.start();
    }

    public void pauseAudio(View view) {
        mediaPlayer.pause();
    }

    public void stopAudio(View view) {
        mediaPlayer.stop();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_demo);

        mediaPlayer = MediaPlayer.create(this, R.raw.kid);


        //used to get info on the volume of device
        audioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);



        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);

        //set maximum volume
        volumeControl.setMax(maxVolume);

        //set current volume
        volumeControl.setProgress(currVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("SeekBar value", Integer.toString(progress));

                //changing the volume
                audioManager.setStreamVolume(audioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
