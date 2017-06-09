package com.ch.wchhuangya.dzah.android.activity.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wchya on 2017-06-06 20:46
 */

public class TaoBaoItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {android.R.attr.listDivider};
    private Drawable mDrawable;
    private int mCount;
    private int mColumn;
    private int mRow;
    private Paint mPaint;

    public TaoBaoItemDecoration(Context context, int count, int column) {
        mCount = count;
        mColumn = column;
        mRow = count / column;

        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDrawable = typedArray.getDrawable(0);
        typedArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setARGB(255, 14, 15, 16);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == 0)
                continue;
            else {
                View child = parent.getChildAt(i);
                int width = child.getWidth();
                int height = child.getHeight();
                /*if (i % mColumn == 0) {
                    mDrawable.setBounds(0, 0, 0, height);
                    mDrawable.draw(c);
                } else if (i / mColumn == mRow) {
                    mDrawable.setBounds(0, 0, width, 0);
                    mDrawable.draw(c);
                } else {
                    mDrawable.setBounds(0, 0, width, 0);
                    mDrawable.draw(c);
                }*/
                mDrawable.setBounds(0, 0, 0, width);
                mDrawable.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        /*int position = parent.getChildAdapterPosition(view);
        if (position % mColumn == 0) // 最右边，不绘制右边的线
            outRect.bottom = mDrawable.getIntrinsicHeight();
        else if (position / mColumn == mRow) // 最底下，不绘制底下的线
            outRect.right = mDrawable.getIntrinsicWidth();
        else
            outRect.set(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicWidth());*/
    }
}
