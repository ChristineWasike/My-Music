package com.example.wasike.mymusic.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.wasike.mymusic.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.Songbutton) Button mSongButton;
    @Bind(R.id.savedSongbutton) Button mSavedSongButton;
    @Bind(R.id.mySongbutton) Button mySongbutton;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSongButton.setOnClickListener(this);

        mSavedSongButton.setOnClickListener(this);

        mySongbutton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                //display welcome message from the actionbar
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, "+ user.getDisplayName() + "!");
                } else {
                    getSupportActionBar().setTitle("Welcome, " + "Stranger!");

                }

            }
        };
    }

    //message that loads up when the search process is just about to start
    public void toasteMe(View view) {
        Toast myToast = Toast.makeText(this, "Searching!", Toast.LENGTH_SHORT);
        myToast.show();

    }

    //three buttons present to carry out three specific functions of loading up a new activity
    @Override
    public void onClick(View view) {

        //loads the activity with the search results
        if (view == mSongButton) {
            Intent intent = new Intent(this, MTActivity.class);
            Toast myToast = Toast.makeText(this, "Searching!", Toast.LENGTH_SHORT);
            myToast.show();
            startActivity(intent);
        }

        //loads the activity containing saved songs
        if (view == mSavedSongButton) {
            Intent intent = new Intent(MainActivity.this, SavedSongListActivity.class);
            startActivity(intent);
        }

        //loads up the activity containing songs from a user's device
        if (view == mySongbutton) {
            Intent intent = new Intent(this, PhoneStore.class);
            startActivity(intent);
        }
    }

    //responsible for the overflow menu that holds the Login/Logout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        if(mAuth.getCurrentUser()==null){
            MenuItem item= menu.findItem(R.id.action_logout);
            item.setVisible(false);

        }else{
            MenuItem item= menu.findItem(R.id.action_login);
            item.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);


    }

    //this displays the options from the overflow menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_login){
            login();
            return true;
        }
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //loads up when the activity is launched once a user has been successfully authenticated
    @Override
    public  void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    //Removes listener once a user is logged in
    @Override
    public void onStop(){
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void login() {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
