package com.ch.wchhuangya.dzah.android.activity.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.util.ParamHelper;

/**
 * 基本图形绘制
 * Created by wchya on 2016/6/16.
 */
public class BasicGraphActivity extends BaseActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(params);
        iv.setBackgroundResource(android.R.color.white);
        setContentView(iv);

        activity = this;
        drawGraph();
    }

    private void drawGraph() {
        // 画布的配置
        Bitmap bitmap = Bitmap.createBitmap(ParamHelper.getEqumentWidth(activity), ParamHelper.getEqumentHeight(activity), Bitmap.Config.ARGB_8888);//配置

        // 创建画布
        Canvas canvas = new Canvas(bitmap);

        int width = ParamHelper.getEqumentWidth(activity) / 7;
        int height = width;
        float size = width * 0.6f;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(android.R.color.black));

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(0);

        Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(getResources().getColor(android.R.color.black));
        paintText.setStrokeWidth(3);
        paintText.setTextSize(size);

        int initX  = (int) ((width - paintText.measureText("日")) / 2);
        Paint.FontMetrics fontMetrics = paintText.getFontMetrics();

        int initY = (int) (height /2 - fontMetrics.descent + (fontMetrics.bottom - fontMetrics.top) / 2);

        for (int i = 0; i < 7; i++) {
            Rect rect = new Rect(1, 1, 1 + width * ( i + 1), 1 + height);
            canvas.drawRect(rect, paint);
            canvas.drawText("日", initX + i * width, initY, paintText);
        }
        iv.setBackgroundDrawable(new BitmapDrawable(bitmap));
    }
}
