package com.luowei.nestedscrollingdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;

/**
 * Created by Luowei on 2015/10/26.
 */
public class MiddleView extends FrameLayout implements NestedScrollingChild, NestedScrollingParent {
    private NestedScrollingParentHelper parentHelper;
    private NestedScrollingChildHelper childHelper;
    private int[] offsetInWindow = new int[2];
    public MiddleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        childHelper = new NestedScrollingChildHelper(this);
        parentHelper = new NestedScrollingParentHelper(this);
        setNestedScrollingEnabled(true);

    }


    //NestScrollingParent
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        //将事件分发到父类
        startNestedScroll(ViewCompat.SCROLL_AXIS_HORIZONTAL | ViewCompat.SCROLL_AXIS_VERTICAL);
        return true;
//        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        parentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(View child) {
        parentHelper.onStopNestedScroll(child);
        //将事件分发到父类
        stopNestedScroll();
    }


    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        //将事件分发到父类
        if (dispatchNestedPreScroll(dx, dy, consumed, null)) {
            dx -= consumed[0];
            dy -= consumed[1];
        }

        if (dx > 0) {
            if (target.getRight() + dx > getWidth()) {
                dx = target.getRight() + dx - getWidth();
                offsetLeftAndRight(dx);
                consumed[0] += dx;
            }
        } else {
            if (target.getLeft() + dx < 0) {
                dx = target.getLeft() + dx;
                offsetLeftAndRight(dx);
                consumed[0] += dx;
            }
        }

        if (dy > 0) {
            if (target.getBottom() + dy > getHeight()) {
                dy = target.getBottom() +dy- getHeight();
                offsetTopAndBottom(dy);
                consumed[1] += dy;
            }
        } else {
            if (target.getTop() + dy < 0) {
                dy = target.getTop() + dy;
                offsetTopAndBottom(dy);
                consumed[1] += dy;
            }
        }
//
//        offsetLeftAndRight(dx);
//        offsetTopAndBottom(dy);

    }
    @Override
      public int getNestedScrollAxes() {
        return parentHelper.getNestedScrollAxes();
    }


    //NestedScrollingChild
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
