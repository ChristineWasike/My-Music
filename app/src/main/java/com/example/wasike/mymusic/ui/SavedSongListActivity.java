package com.example.wasike.mymusic.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wasike.mymusic.Constants;
import com.example.wasike.mymusic.R;
import com.example.wasike.mymusic.adapters.FirebaseSongListAdapter;
import com.example.wasike.mymusic.adapters.FirebaseSongViewHolder;
import com.example.wasike.mymusic.model.Song;
import com.example.wasike.mymusic.util.OnStartDragListener;
import com.example.wasike.mymusic.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedSongListActivity extends AppCompatActivity implements OnStartDragListener{
    private DatabaseReference mSongReference;
    private FirebaseSongListAdapter mFirebaseAdapter;
    private FirebaseAuth mAuth;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.progressBar) ProgressBar mProgressBar;
    @Bind(R.id.titleTextView) TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();


        mAuth = FirebaseAuth.getInstance();

//        if (mAuth.getCurrentUser() != null) {
//        } else {
//            mTextView.setText("You are not logged in. Click here to log in.");
//            mTextView.setOnClickListener(this);
//        }
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mSongReference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_SONGS)
                .child(uid);


        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_SONGS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseSongListAdapter(Song.class, R.layout.song_list_item_drag, FirebaseSongViewHolder.class, query,this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);


    }

//    @Override
//    public void onClick(View view) {
//        if (view == mTextView) {
//            Intent intent = new Intent(SavedSongListActivity.this, LoginActivity.class);
//            startActivity(intent);
//
//        }
//    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }


}
