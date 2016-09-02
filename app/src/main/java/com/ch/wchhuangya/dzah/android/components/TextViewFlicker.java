package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 文字闪烁
 * Created by wchya on 16/9/1.
 */
public class TextViewFlicker extends TextView {
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private Paint mPaint;
    private int mViewWidth = 0;
    private int mTranslate = 0;

    public TextViewFlicker(Context context) {
        super(context);
    }

    public TextViewFlicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewFlicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                // 此步灰常重要,作用是获取当前绘制 TextView 的 Paint 对象,并给这个 Paint 对象设置原生 TextView 没有的 LinearGradient。
                mPaint = getPaint();
                /** 参数依次是:
                 *  1. 梯度线 x 坐标的起始点
                 *  2. 梯度线 y 坐标的起始点
                 *  3. 梯度线 x 坐标的结束点
                 *  4. 梯度线 y 坐标的结束点
                 *  5. 沿着梯度线分布的颜色
                 *  6. 可以为 null。如果不为 null,第 5 个参数中的颜色分别对应着这里面的一个相对位置的值(值的范围:[0,1])；如果为 null,颜色将沿着梯度线均匀分布
                 *  7. 着色器平铺模式
                 **/
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[]{Color.BLUE, 0xffffffff, Color.BLUE}, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mMatrix);
            postInvalidateDelayed(150);
        }
    }
}
