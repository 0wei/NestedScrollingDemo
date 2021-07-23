package com.luowei.nestedscrollingdemo.behaviors;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;


/**
 * Created by LuoWei on 2016/3/22.
 */
public class MyBehavior extends CoordinatorLayout.Behavior {

    public MyBehavior() {
        super();
    }

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
//        Logger.i("");
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
//        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
//        Logger.i("");

        return (nestedScrollAxes & View.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
//        Logger.i("");

    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
//        Logger.i("  dxConsumed= %d  dyConsumed= %d  dxUnconsumed= %d  dyUnconsumed= %d", dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
//        Logger.i(" dx= %d, dy= %d, consumed[0]= %d,consumed[1]= %d", dx, dy, consumed[0], consumed[1]);
        dy = dy / 2;
        //简单消耗掉一半的距离
        consumed[1]+= dy;
        child.offsetTopAndBottom(dy);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
//        Logger.i("");
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);

    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
//        Logger.i("");
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);

    }
}
