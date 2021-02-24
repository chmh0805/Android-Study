//package com.example.roomex1;
//
//import android.graphics.Canvas;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.RecyclerView;
//
//import static java.lang.Float.max;
//import static java.lang.Float.min;
//
//public class SwipeController extends ItemTouchHelper.Callback {
//    private int currentPosition;
//    private int previousPosition;
//    private float currentDx;
//    private float clamp;
//
//    @Override
//    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//        return makeMovementFlags(0, 1 << 2 | 1 << 3);
//    }
//
//    @Override
//    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//        return false;
//    }
//
//    @Override
//    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//    }
//
//    @Override
//    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//        currentDx = 0f;
//        previousPosition = viewHolder.getAdapterPosition();
//        getDefaultUIUtil().clearView(getView(viewHolder));
//    }
//
//    @Override
//    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
//        currentPosition = viewHolder.getAdapterPosition();
//        super.onSelectedChanged(viewHolder, actionState);
//    }
//
//    @Override
//    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            View view = getView(viewHolder);
//            boolean isClamped = getTag(viewHolder);
//
//            getDefaultUIUtil().onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive);
//        }
//    }
//
//    @Override
//    public float getSwipeEscapeVelocity(float defaultValue) {
//        return defaultValue * 10;
//    }
//
//    @Override
//    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
//        boolean isClamped = getTag(viewHolder);
//        setTag(viewHolder, !isClamped && currentDx <= -clamp);
//        return 2f;
//    }
//
//    private View getView(RecyclerView.ViewHolder viewHolder) {
//        MyAdapter.MyViewHolder viewHolder1 = (MyAdapter.MyViewHolder) viewHolder;
//        return viewHolder1.swipeView;
//    }
//
//    private void setTag(RecyclerView.ViewHolder viewHolder, boolean isClamped) {
//        viewHolder.itemView.setTag(isClamped);
//    }
//
//    private boolean getTag(RecyclerView.ViewHolder viewHolder) {
//        return (boolean)viewHolder.itemView.getTag();
//    }
//
//    private float clampViewPositionHorizontal(View view, float dX, boolean isClamped, boolean isCurrentlyActive) {
//        float minVal = (float)-view.getWidth()/2;
//        float maxVal = 0f;
//        float x = 0;
//
//        if (isClamped) {
//            if (isCurrentlyActive) {
//                x = dX - clamp;
//            } else {
//                x = -clamp;
//            }
//        } else {
//            x = dX;
//        }
//
//        return min(max(minVal, x), maxVal);
//    }
//}
