package com.example.wasike.mymusic.util;

/**
 * Created by wasike on 25/09/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
