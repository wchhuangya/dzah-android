package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测量模式自定义视图
 * Created by wchya on 16/8/29.
 */
public class MeasureModelView extends View {

    public MeasureModelView(Context context) {
        super(context);
    }

    public MeasureModelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width, height;

        if (widthModel == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = 200;
            if (widthModel == MeasureSpec.AT_MOST)
                width = Math.min(width, widthSize);
        }

        if (heightModel == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = 120;
            if (heightModel == MeasureSpec.AT_MOST)
                height = Math.min(height, heightSize);
        }

        setMeasuredDimension(width, height);
    }
}
