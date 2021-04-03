package com.gigs.maher1957.Helper;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.gigs.maher1957.Adapters.CartAdapter;
import com.gigs.maher1957.Adapters.CartAdapterV2;

public class RecycleItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private RecycleItemTouchHelperListener listener;

    public RecycleItemTouchHelper(int dragDirs, int swipeDirs, RecycleItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        if (listener != null)
            listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());

    }
    @Override
    public void clearView(RecyclerView recycleview, RecyclerView.ViewHolder viewHolder){
        View foregroundView = ((CartAdapterV2.CartViewHolder)viewHolder).itemView;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection){
        return super.convertToAbsoluteDirection(flags,layoutDirection);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder,int actionState){
        if(viewHolder != null){
            View foregroundView = ((CartAdapterV2.CartViewHolder)viewHolder).itemView;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundView = ((CartAdapterV2.CartViewHolder) viewHolder).itemView;
        getDefaultUIUtil().onDrawOver(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundView = ((CartAdapterV2.CartViewHolder) viewHolder).itemView;
        getDefaultUIUtil().onDraw(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
    }
}
