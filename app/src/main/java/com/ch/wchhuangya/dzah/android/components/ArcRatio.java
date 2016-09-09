package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.LogHelper;

/**
 * 自定义View —— 弧形比例
 * Created by wchya on 16/9/8.
 */
public class ArcRatio extends View {
    /** 控件整个的宽度 */
    private int mWidth;
    /** 控件整个的高度 */
    private int mHeight;
    /** 弧线的颜色,默认绿色 */
    private int mArcColor;
    /** 弧线的宽度,默认宽度 50 */
    private float mArcWidth;
    /** 弧线的弧度,默认 360 */
    private float mArcRadian;
    /** 弧线和圆形之间的距离,默认 20 */
    private int mDisBetweenArcAndCircle;
    /** 圆形的颜色,默认绿色 */
    private int mCircleColor;
    /** 圆形中间的文字 */
    private String mTitleText;
    /** 圆形中间文字的颜色,默认白色 */
    private int mTitleColor;
    /** 圆形中间文字的尺寸,默认 22 */
    private int mTitleSize;

    /** 弧形外接椭圆的外切长方形 */
    private RectF mRectF;
    /** 弧线的画笔 */
    private Paint mArcPaint;
    /** 圆环画笔 */
    private Paint mDonutPaint;
    /** 圆形的画笔 */
    private Paint mCirclePaint;
    /** 圆形中心点的 X 坐标 */
    private int mCircleX;
    /** 圆形中心点的 y 坐标 */
    private int mCircleY;
    /** 圆形的半径 */
    private int mCircleRadius;
    /** 标题的画笔 */
    private Paint mTitlePaint;
    /** 标题开始绘制的 X 坐标 */
    private int mTitleX;
    /** 标题开始绘制的基线 Y 坐标 */
    private int mTitleBaselineY;

    public ArcRatio(Context context) {
        super(context);
    }

    public ArcRatio(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcRatio);

        mArcColor = typedArray.getColor(R.styleable.ArcRatio_arcColor, getResources().getColor(R.color.arc_ratio_green));
        mArcWidth = typedArray.getDimension(R.styleable.ArcRatio_arcWidth, 50.0f);
        mArcRadian = typedArray.getFloat(R.styleable.ArcRatio_arcRadian, 360.0f);
        mDisBetweenArcAndCircle = typedArray.getDimensionPixelOffset(R.styleable.ArcRatio_disBetweenArcAndCircle, 20);
        mCircleColor = typedArray.getColor(R.styleable.ArcRatio_circleColor, getResources().getColor(R.color.arc_ratio_green));
        mTitleText = typedArray.getString(R.styleable.ArcRatio_titleText);
        if (mTitleText != null)
            mTitleText += "(" + mArcRadian + "%)";
        mTitleColor = typedArray.getColor(R.styleable.ArcRatio_titleColor, getResources().getColor(android.R.color.white));
        mTitleSize = typedArray.getInteger(R.styleable.ArcRatio_titleSize, 22);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获取自定义控件的宽度、高度
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);

        init();
    }

    /** 初始化画笔等参数 */
    private void init() {
        int ultimateVal = 0;

        if (mHeight >= mWidth)
            ultimateVal = mWidth;
        else
            ultimateVal = mWidth;


        LogHelper.d(ArcRatio.class, getLeft() + "  " + getRight() + "  " + getTop() + "  " + getBottom());
        // 因为圆弧的线有宽度,因此,需要减去圆弧宽度的一半
        mRectF = new RectF(getPaddingLeft() + mArcWidth / 2, getPaddingTop() + mArcWidth / 2,
                            ultimateVal - getPaddingRight() - mArcWidth / 2, ultimateVal - getPaddingBottom() - mArcWidth / 2);
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mArcWidth);
        mArcPaint.setColor(mArcColor);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(mCircleColor);
        mCircleX = ultimateVal / 2;
        mCircleY = ultimateVal / 2;
        mCircleRadius = (int) ((ultimateVal - getPaddingLeft() - getPaddingRight() - mArcWidth - mDisBetweenArcAndCircle) / 2);

        if (mTitleText != null) {
            mTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mTitlePaint.setColor(mTitleColor);
            mTitlePaint.setTextSize(mTitleSize);
            mTitleX = (int) (mCircleX - mTitlePaint.measureText(mTitleText) / 2);
            mTitleBaselineY = (int) (mCircleY - (mTitlePaint.ascent() + mTitlePaint.descent()));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*Paint rectFPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectFPaint.setStyle(Paint.Style.STROKE);
        rectFPaint.setColor(getResources().getColor(android.R.color.black));
        canvas.drawRect(mRectF, rectFPaint);*/
        canvas.drawArc(mRectF, -90, (float) (mArcRadian * 0.01 * 360), false, mArcPaint);
        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mCirclePaint);
        if (mTitleText != null)
            canvas.drawText(mTitleText, mTitleX, mTitleBaselineY, mTitlePaint);
    }
}
