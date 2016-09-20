package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.ch.wchhuangya.dzah.android.util.ScreenHelper;

/**
 * 侧滑
 * Created by wchya on 16/9/15.
 */
public class Sideslip extends HorizontalScrollView {

    private Context mContext;
    /** 屏幕的宽度,单位像素 */
    private int mWidth;
    /** 屏幕的高度,单位像素 */
    private int mHeight;
    /** 是否第一次的标识 */
    private boolean isFirst;
    /** 左侧菜单的宽度 */
    private int mLeftWidth;
    /** 左侧菜单显示出多少的宽度,松手后菜单整个显示出来 */
    private int mShowWidthBounds;

    /** 左侧菜单拉出来后,菜单的右侧边缘离屏幕右边的距离,单位——dp */
    private int mRightPadding = 50;

    public Sideslip(Context context) {
        super(context);

        init(context);
    }

    public Sideslip(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public Sideslip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mWidth = ScreenHelper.getScreenWidth(context);
        mHeight = ScreenHelper.getScreenHeight(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (isFirst) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            ViewGroup left = (ViewGroup) wrapper.getChildAt(0);
            ViewGroup middle = (ViewGroup) wrapper.getChildAt(1);

            mRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mRightPadding, mContext.getResources().getDisplayMetrics());
            mShowWidthBounds = mRightPadding / 3;

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(mLeftWidth, 0);
            isFirst = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                if (getScrollX() > mShowWidthBounds) {
                    this.smoothScrollTo(mLeftWidth, 0);
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
