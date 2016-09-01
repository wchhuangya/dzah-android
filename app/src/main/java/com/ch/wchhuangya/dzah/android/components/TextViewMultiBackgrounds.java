package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 绘制带有双框的自定义 TextView
 * Created by wchya on 16/9/1.
 */
public class TextViewMultiBackgrounds extends TextView {
    private Paint mPaint1; // 外框画笔
    private Paint mPaint2; // 内框画笔

    public TextViewMultiBackgrounds(Context context) {
        super(context);

        init();
    }

    public TextViewMultiBackgrounds(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public TextViewMultiBackgrounds(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private void init() {
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 在回调父类方法之前,实现自己的逻辑,对 TextView 来说即是在绘制文本内容之前
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint2);
        canvas.save();
        // 绘制文字前平移10 像素
        canvas.translate(10, 10);
        // 使用父类的方法绘制文本
        super.onDraw(canvas);
        // 在回调父类方法之后,实现自己的逻辑,对 TextView 来说即是在绘制文本内容之后

        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width, height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = 200; // 这里的默认值根据实际情况修改
            if (widthMode == MeasureSpec.AT_MOST)
                width = Math.min(width, widthSize);
        }

        if (heightMode == MeasureSpec.EXACTLY	) {
            height = heightSize;
        } else {
            height = 200; // 这里的默认值根据实际情况修改
            if (heightMode == MeasureSpec.AT_MOST)
                height = Math.min(height, heightSize);
        }

        setMeasuredDimension(width, height);
    }
}
