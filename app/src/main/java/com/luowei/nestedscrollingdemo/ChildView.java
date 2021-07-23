package com.luowei.nestedscrollingdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;


public class ChildView extends View implements NestedScrollingChild {
    private NestedScrollingChildHelper childHelper;
    private float ox;
    private float oy;
    private int[] consumed = new int[2];
    private int[] offsetInWindow = new int[2];

    public ChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        childHelper = new NestedScrollingChildHelper(this);
        //
        setNestedScrollingEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ox = event.getX();
                oy = event.getY();
                //开始滑动
                startNestedScroll(ViewCompat.SCROLL_AXIS_HORIZONTAL | ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            case MotionEvent.ACTION_MOVE:

                float clampedX = event.getX();

                float clampedY = event.getY();


                int dx = (int) (clampedX - ox);
                int dy = (int) (clampedY - oy);

                //分发触屏事件给父类处理
                if (dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)) {
                    //减掉父类消耗的距离
                    dx -= consumed[0];
                    dy -= consumed[1];
                }
                offsetLeftAndRight(dx);
                offsetTopAndBottom(dy);
                break;
            case MotionEvent.ACTION_UP:
                stopNestedScroll();
                break;
        }
        return true;/*super.onTouchEvent(event);*/
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        childHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return childHelper.isNestedScrollingEnabled();

    }

    @Override
    public boolean startNestedScroll(int axes) {
        return childHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        childHelper.stopNestedScroll();

    }

    @Override
    public boolean hasNestedScrollingParent() {
        return childHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    /**
     * @param dx       水平滑动距离
     * @param dy       垂直滑动距离
     * @param consumed 父类消耗掉的距离
     * @return
     */
    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return childHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return childHelper.dispatchNestedPreFling(velocityX, velocityY);
    }
}
