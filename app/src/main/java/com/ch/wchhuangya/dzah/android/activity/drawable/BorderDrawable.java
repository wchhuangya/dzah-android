package com.ch.wchhuangya.dzah.android.activity.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Created by wchya on 2017-02-21 17:39
 */

public class BorderDrawable extends Drawable {

    private Paint mPaint;
    private Path mPath;
    private RectF mRectF;
    private int mColor;
    private int mBorderWidth;
    private int mBorderRadius;

    public BorderDrawable(int color, int borderWidth, int borderRadius) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
        mPath.setFillType(Path.FillType.EVEN_ODD);

        mRectF = new RectF();

        mColor = color;
        mBorderWidth = borderWidth;
        mBorderRadius = borderRadius;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mPath.reset();

        mPath.addRect(bounds.left, bounds.top, bounds.right, bounds.bottom, Path.Direction.CW);
        mRectF.set(bounds.left + mBorderWidth, bounds.top + mBorderWidth, bounds.right - mBorderWidth, bounds.bottom - mBorderWidth);
        mPath.addRoundRect(mRectF, mBorderRadius, mBorderRadius, Path.Direction.CW);
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
