package com.gigs.maher1957.Helper;

import androidx.recyclerview.widget.RecyclerView;

public interface RecycleItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
