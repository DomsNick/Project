package com.codetribe.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by CodeTribe on 2017/10/04.
 */

public class RecyclerViewCardListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mListener;

    public interface  OnItemClickListener {
        public void  onItemClick(View view, int position);
    }

    GestureDetector gestureDetector;

    public RecyclerViewCardListener(Context context,OnItemClickListener listener){

        mListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View view = rv.findChildViewUnder(e.getX(),e.getY());
        if (view != null && mListener !=null && gestureDetector.onTouchEvent(e)){

            mListener.onItemClick(view, rv.getChildAdapterPosition(view));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
