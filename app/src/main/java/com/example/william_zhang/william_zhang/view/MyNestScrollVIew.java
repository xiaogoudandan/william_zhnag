package com.example.william_zhang.william_zhang.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by william_zhang on 2017/9/5.
 */

public class MyNestScrollVIew extends NestedScrollView {
    private int slop;

    private float mInitialMotionX;

    private float mInitialMotionY;



    public MyNestScrollVIew(Context context) {
        super(context);
        init(context);
    }



    private void init(Context context) {
        ViewConfiguration config = ViewConfiguration.get(context);
        slop = config.getScaledEdgeSlop();
    }

    public MyNestScrollVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private float xDistance, yDistance, lastX, lastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                lastX = ev.getX();
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - lastX);
                yDistance += Math.abs(curY - lastY);
                lastX = curX;
                lastY = curY;
                if (xDistance > yDistance)
                    return false;
        }


        return super.onInterceptTouchEvent(ev) || ev.getPointerCount() == 2;

    }
}
