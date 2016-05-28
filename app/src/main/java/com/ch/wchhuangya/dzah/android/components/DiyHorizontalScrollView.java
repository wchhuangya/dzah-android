package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.ActivityHelper;
import com.ch.wchhuangya.dzah.android.util.LogHelper;

/**
 * 自定义横向滚动菜单
 * Created by wchya on 2016/5/19.
 */
public class DiyHorizontalScrollView extends HorizontalScrollView {
    /** 横向滚动条的直接子元素 */
    private ViewGroup mWrapper;
    /** 左边内容的容器 */
    private ViewGroup mLeftMenu;
    /** 右边内容的容器 */
    private ViewGroup mRightContent;
    /** 当前屏幕的宽度 */
    private int mScreenWidth;
    /** 左侧内容的宽度 */
    private int mLeftWidth;
    /** 右侧内容的宽度 */
    private int mRightWidth;
    /** 左侧内容拖出后，内容左边与屏幕右边界的距离 */
    private int mRightMargin = 150;
    /** 用于标识是否首次的变量 */
    private boolean isFirst;
    /** 上下文 */
    private Context mContext;
    /** 标识当前子元素的位置 */
    private int pos = 1;
    /** 手点下去时的点 */
    private float firstPos;
    /** 手抬起来时的点 */
    private float lastPos;

    public DiyHorizontalScrollView(Context context) {
        this(context, null);
        mContext = context;
    }

    public DiyHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    public DiyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DiyHorizontalScrollView, defStyleAttr, 0);
        int indexCount = array.getIndexCount();
        for (int i = 0; i < indexCount; i++)
            switch (array.getIndex(i)) {
                case R.styleable.DiyHorizontalScrollView_rightMargin:
                    mRightMargin = array.getDimensionPixelSize(defStyleAttr, ActivityHelper.changeUnit(context, TypedValue.COMPLEX_UNIT_DIP, 100));
                    break;
            }
        array.recycle();

        // 获取屏幕的宽度
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;

        if (mRightMargin == 0)
            mRightMargin = ActivityHelper.changeUnit(context, TypedValue.COMPLEX_UNIT_DIP, mRightMargin);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isFirst) {
            mWrapper = (ViewGroup) getChildAt(0);
            mLeftMenu = (ViewGroup) mWrapper.getChildAt(0);
            mRightContent = (ViewGroup) mWrapper.getChildAt(1);
            mLeftWidth = mLeftMenu.getLayoutParams().width = mScreenWidth - mRightMargin;
            mRightWidth = mRightContent.getLayoutParams().width = mScreenWidth;
            isFirst = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!changed)
            this.smoothScrollTo(mLeftWidth, 0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float x = ev.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                firstPos = x;
                LogHelper.d(DiyHorizontalScrollView.class, "firstPos: " + firstPos);
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float x = ev.getX();
        switch (action) {
            case MotionEvent.ACTION_UP:
                lastPos = x;
                LogHelper.d(DiyHorizontalScrollView.class, "lastPos: " + lastPos);
                    if (getScrollX() >= mLeftWidth / 3 * 2)
                        this.smoothScrollTo(mLeftWidth, 0);
                    else
                        this.smoothScrollTo(0, 0);
                    return true;
        }
        return super.onTouchEvent(ev);
    }

    /** 改变当前可见视图 */
    public void changePos() {
        if (pos == 0) {
            this.smoothScrollTo(mLeftWidth, 0);
            pos = 1;
        } else {
            this.smoothScrollTo(0, 0);
            pos = 0;
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
