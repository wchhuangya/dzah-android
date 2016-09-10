package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.util.LogHelper;

/**
 * 自定义视图测试/复习
 * Created by wchya on 16/9/9.
 */
public class TestCustomView extends TextView {
    private Paint mOutPaint;
    private Paint mInnerPaint;

    public TestCustomView(Context context) {
        super(context);
    }

    public TestCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mOutPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutPaint.setStyle(Paint.Style.FILL);
        mOutPaint.setColor(getResources().getColor(android.R.color.holo_green_light));

        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setStyle(Paint.Style.FILL);
        mInnerPaint.setColor(getResources().getColor(android.R.color.holo_orange_light));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (getPaddingLeft() == 0 || getPaddingRight() == 0 || getPaddingTop() == 0 || getPaddingBottom() == 0)
            LogHelper.d(TestCustomView.class, "没有设置内边距,什么都不会改变!");
        else {

            canvas.drawRect(0, 0, getWidth(), getHeight(), mOutPaint);
            canvas.drawRect(10, 10, getWidth() - 10, getHeight() - 10, mInnerPaint);
        }

        super.onDraw(canvas);
    }
}
