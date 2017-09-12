package com.example.wasike.mymusic;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //refactoring code using bind to prevent unnecessary repetition
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.findLyricsButton)
    Button mFindLyricsButton;
    @Bind(R.id.TitleEditText)
    EditText mTitleEditText;
    @Bind(R.id.headerTextView)
    TextView mTextView;

    //implementing the GridView
    String[] genres = new String[] {"Pop", "Jazz", "Opera",
            "Blues", "Rock", "Punk", "Dub", "Ska",
            "Funk", "House", "EDM", "Soul", "Art"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface openFont = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");
        mTextView.setTypeface(openFont);

        mFindLyricsButton.setOnClickListener(this);

        GridView gridView = (GridView) findViewById(R.id.baseGridView);
        gridView.setAdapter(new GenreAdapter(this, genres));
    }

    public void toasteMe(View view) {
        Toast myToast = Toast.makeText(this, "Searching!", Toast.LENGTH_SHORT);
        myToast.show();

    }

    @Override
    public void onClick(View v) {
        if (v == mFindLyricsButton) {
            String title = mTitleEditText.getText().toString();
            Log.d(TAG, title);
            Intent intent = new Intent(MainActivity.this, LyricsActivity.class);
            intent.putExtra("title", title);
            startActivity(intent);
        }
    }
}
