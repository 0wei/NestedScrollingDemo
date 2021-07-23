package com.luowei.nestedscrollingdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;


/**
 * Created by Luo on 2015/9/17.
 */
public class ParentView extends FrameLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper parentHelper;


    public ParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parentHelper = new NestedScrollingParentHelper(this);
    }

    //子类请求滑动startNestedScroll()
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        //
        return true;// super.onStartNestedScroll(child, target, nestedScrollAxes);
    }


    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        parentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(View child) {
        parentHelper.onStopNestedScroll(child);
    }


    //子类滑动事件分发回调dispatchNestedPreScroll
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (dx > 0) {
            if (target.getRight() + dx > getWidth()) {
                dx = target.getRight() + dx - getWidth();
                offsetLeftAndRight(dx);
                consumed[0] += dx;   //将消耗掉的距离返回给子类
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

    }

    @Override
    public int getNestedScrollAxes() {
        return parentHelper.getNestedScrollAxes();
    }
}
