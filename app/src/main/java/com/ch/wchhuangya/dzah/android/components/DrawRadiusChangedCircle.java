package com.ch.wchhuangya.dzah.android.components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ch.wchhuangya.dzah.android.util.ParamHelper;

/**
 * 自定义控件：绘制一个圆环，每40msn半径就发生变化
 * Created by wchya on 2016/6/8.
 */
public class DrawRadiusChangedCircle extends View implements Runnable {
    private Paint mPaint;
    private Context mContext;
    private int radius = 150;

    public DrawRadiusChangedCircle(Context context) {
        super(context);
        mContext = context;
    }

    public DrawRadiusChangedCircle(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(ParamHelper.getEqumentWidth((Activity)mContext) / 2, ParamHelper.getEqumentHeight((Activity)mContext) / 2, radius, mPaint);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置画笔为描边
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔颜色
        mPaint.setColor(Color.LTGRAY);
        // 设置画笔粗细
        mPaint.setStrokeWidth(10);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (radius <= 150) {
                    radius += 10;
                    postInvalidate();
                } else {
                    radius = 0;
                }
                // 每执行一次暂停40毫秒
                Thread.sleep(140);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
