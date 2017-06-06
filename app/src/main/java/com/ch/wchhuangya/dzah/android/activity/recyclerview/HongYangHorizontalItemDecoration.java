package com.ch.wchhuangya.dzah.android.activity.recyclerview;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wchya on 2017-06-06 15:50
 */

public class HongYangHorizontalItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;
    private int mColumn;
    private int mCount;
    private int mRow;

    public HongYangHorizontalItemDecoration(Drawable drawable, int column, int count) {
        mDrawable = drawable;
        mColumn = column;
        mCount = count;
        mRow = count / column;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        // 第一行：无上边
        if (position < mColumn) {
            if (position != 0)
                outRect.set(mDrawable.getIntrinsicWidth(), 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
            else
                outRect.set(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        } else if (position / mColumn == mRow) // 最后一行：无下边
            outRect.set(mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight(), mDrawable.getIntrinsicWidth(), 0);
    }
}
