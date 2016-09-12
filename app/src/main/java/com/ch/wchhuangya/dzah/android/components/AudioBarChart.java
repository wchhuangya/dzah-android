package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.ch.wchhuangya.dzah.android.R;

import java.util.Random;

/**
 * 仿音频条状图
 * Created by wchya on 16/9/10.
 */
public class AudioBarChart extends View {
    /** 条的宽度,默认 20 */
    private int mBarWidth;
    /** 条的起始颜色,默认颜色——红色:#EE3D11 */
    private int mBarStartColor;
    /** 条的结束颜色,默认颜色——橙色:#D5B32B */
    private int mBarEndColor;
    /** 条间距,默认 5 */
    private int mBarSpace;
    /** View 的背影颜色,默认颜色——白色 */
    private int mWholeBgColor;
    /** 条变化的频率,单位毫秒,默认 300 */
    private int mChangeFrequency;

    /** 绘制条形图的画笔 */
    private Paint mBarPaint;
    /** 控制条颜色的线性梯度对象 */
    private LinearGradient mLinearGradient;

    /** 控件宽度 */
    private int mWidth;
    /** 控件高度 */
    private int mHeight;
    /** 能绘制的条的总数 */
    private int mBarCount;

    public AudioBarChart(Context context) {
        super(context);
    }

    public AudioBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AudioBarChart);

        mBarWidth = (int) typedArray.getDimension(R.styleable.AudioBarChart_barWidth, 20);
        mBarStartColor = typedArray.getColor(R.styleable.AudioBarChart_barStartColor, getResources().getColor(R.color.audio_bar_chart_red));
        mBarEndColor = typedArray.getColor(R.styleable.AudioBarChart_barEndColor, getResources().getColor(R.color.audio_bar_chart_orange));
        mBarSpace = (int) typedArray.getDimension(R.styleable.AudioBarChart_barSpace, 5);
        mWholeBgColor = typedArray.getColor(R.styleable.AudioBarChart_wholeBgColor, getResources().getColor(android.R.color.white));
        mChangeFrequency = typedArray.getInt(R.styleable.AudioBarChart_changeFrenquency, 300);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);

        mBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBarPaint.setStyle(Paint.Style.FILL);

        mBarCount = (mWidth - mBarSpace * 2) / (mBarWidth + mBarSpace);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setBackgroundColor(mWholeBgColor);
        for (int i = 0; i < mBarCount; i++) {
            int height = mHeight - new Random().nextInt(mHeight * 3 / 4);
            int x = (i + 1) * mBarSpace + i * mBarWidth;
            Rect rect = new Rect(x, height, x + mBarWidth, mHeight);
            mLinearGradient = new LinearGradient(x, mHeight, x + mBarWidth, height, mBarStartColor, mBarEndColor, Shader.TileMode.CLAMP);
            mBarPaint.setShader(mLinearGradient);
            canvas.drawRect(rect, mBarPaint);
        }
        postInvalidateDelayed(mChangeFrequency);
    }
}
