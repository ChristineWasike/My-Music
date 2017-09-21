package com.example.wasike.mymusic.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.ui.MTActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.SongEditText)EditText mSongEditText;
    @Bind(R.id.Songbutton) Button mSongButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSongButton.setOnClickListener(this);
    }

    public void toasteMe(View view) {
        Toast myToast = Toast.makeText(this, "Searching!", Toast.LENGTH_SHORT);
        myToast.show();

    }

    @Override
    public void onClick(View view) {
        if (view == mSongButton) {
            String title = mSongEditText.getText().toString();
            Log.d(TAG, title);
            Intent intent = new Intent(this, MTActivity.class);
            intent.putExtra("title", title);
            startActivity(intent);
        }
    }
}
