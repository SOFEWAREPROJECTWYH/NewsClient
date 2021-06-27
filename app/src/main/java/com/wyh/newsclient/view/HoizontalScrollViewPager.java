package com.wyh.newsclient.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;


public class HoizontalScrollViewPager extends ViewPager {

    private float startX;
    private float startY;

    public HoizontalScrollViewPager(Context context) {
        super(context);
    }

    public HoizontalScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                startX = ev.getX();
                startY = ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);

                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getY();

                float deltaX = moveX - startX;
                float deltaY = moveY - startY;

                if (Math.abs(deltaX) > Math.abs(deltaY)) {

                    if (getCurrentItem() == 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }else{
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }

                }else{
                    getParent().requestDisallowInterceptTouchEvent(false);
                }

                break;
            default:

                break;
        }

        return super.onTouchEvent(ev);
    }
}
