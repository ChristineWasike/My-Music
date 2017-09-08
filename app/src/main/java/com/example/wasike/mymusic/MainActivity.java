package com.example.wasike.mymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.onClickListener {
    //refactoring code using bind to prevent unnecessary repetition
    @Bind(R.id.findLyricsButton)
    Button mFindLyricsButton;
    @Bind(R.id.TitleEditText) Button mTitleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindLyricsButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mFindLyricsButton) {
            String title = mTitleEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, Lyr);
        }
    }


    public void toasteMe(View view) {
        Toast myToast = Toast.makeText(this, "Searching!", Toast.LENGTH_SHORT);
        myToast.show();
    }
}
