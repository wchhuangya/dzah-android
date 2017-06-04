package com.ch.wchhuangya.dzah.android.graphics;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.DimenUtil;
import com.ch.wchhuangya.dzah.android.util.ScreenHelper;

/**
 * Created by wchya on 2017-02-22 09:24
 */

public class BasicGraphics extends View implements Runnable {

    public static final int DEFAULT_TYPE = 1;
    public static final int TYPE_CIRCLE = 1; // 圆点
    public static final int TYPE_LINE = 2; // 直线
    public static final int TYPE_DONUT = 3; // 圆环
    public static final int TYPE_ARGB = 4; // 带透明度的颜色填充
    public static final int TYPE_RGB = 5; // 不带透明度的颜色填充
    private Context mContext;
    private int mBgColor;
    private int mType;
    private Paint mCirclePaint; // 圆画笔
    private Paint mLinePaint; // 直线画笔
    private Paint mBonutPaint; // 折线画笔
    private float mLineLength;
    private int mBonutRate;
    private int mHeight; //
    private int mMaxRadius; // 圆环可以达到的最大半径
    private int mRealRadius; // 圆环真实的半径

    public BasicGraphics(Context context) {
        super(context);
    }

    public BasicGraphics(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BasicGraphics);

        mBgColor = typedArray.getColor(R.styleable.BasicGraphics_v_bg, getResources().getColor(android.R.color.holo_red_light));
        mType = typedArray.getInt(R.styleable.BasicGraphics_v_type, DEFAULT_TYPE);
        int paintColor = typedArray.getColor(R.styleable.BasicGraphics_v_paint_color, getResources().getColor(android.R.color.holo_green_light));
        mLineLength = typedArray.getInteger(R.styleable.BasicGraphics_v_length, 0);
        mBonutRate = typedArray.getInteger(R.styleable.BasicGraphics_v_rate, 50);

        typedArray.recycle();

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(paintColor);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mLinePaint.setColor(paintColor);

        mBonutPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBonutPaint.setStyle(Paint.Style.STROKE);
        mBonutPaint.setStrokeWidth(5);
        mBonutPaint.setColor(paintColor);
        mRealRadius = 15;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mType != TYPE_ARGB)
            setBackgroundColor(mBgColor);
        switch (mType) {
            case TYPE_CIRCLE:
                canvas.drawCircle(15, 15, 15, mCirclePaint);
                break;
            case TYPE_LINE:
                canvas.drawLine(0, 20, mLineLength, 20, mLinePaint);
                break;
            case TYPE_DONUT:
                mMaxRadius = (mHeight - 10) / 2;

                canvas.drawCircle((ScreenHelper.getScreenWidth(mContext) - DimenUtil.dip2px(mContext, 25) * 2) / 2,
                        mMaxRadius + 5, mRealRadius, mBonutPaint);
                break;
            case TYPE_ARGB:
                canvas.drawARGB(10, 211,11,101);
                break;
            case TYPE_RGB:
                canvas.drawRGB(211, 11, 101);
                break;
        }
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
            width = 100; // 这里的默认值根据实际情况修改
            if (widthMode == MeasureSpec.AT_MOST)
                width = Math.min(width, widthSize);
        }

        if (heightMode == MeasureSpec.EXACTLY	) {
            height = heightSize;
        } else {
            height = 100; // 这里的默认值根据实际情况修改
            if (heightMode == MeasureSpec.AT_MOST)
                height = Math.min(height, heightSize);
        }

        if (mType == TYPE_DONUT)
            mHeight = height;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (mRealRadius < mMaxRadius)
                    mRealRadius += 10;
                else
                    mRealRadius = 15;

                postInvalidate();

                Thread.sleep(mBonutRate);
            } catch (Exception ex) {
                Log.d(Constant.DZAH_TAG, ex.getMessage());
            }
        }
    }
}
