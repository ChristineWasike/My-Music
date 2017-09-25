package com.example.wasike.mymusic.adapters;

import android.content.Context;

import com.example.wasike.mymusic.model.Song;
import com.example.wasike.mymusic.util.ItemTouchHelperAdapter;
import com.example.wasike.mymusic.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by wasike on 25/09/17.
 */

public class FirebaseSongListAdapter extends FirebaseRecyclerAdapter<Song, FirebaseSongViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseSongListAdapter(Class<Song> modelClass, int modelLayout, Class<FirebaseSongViewHolder> viewHolderClass,
                                   Query ref,OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseSongViewHolder viewHolder, Song model, int position) {
    viewHolder.bindSong(model);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }


}
