package com.ch.wchhuangya.dzah.android.activity.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wchya on 2017-06-06 08:43
 */

public class XampItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;

    public XampItemDecoration(Drawable drawable) {
        mDrawable = drawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = mDrawable.getIntrinsicHeight() + top;
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        /*if (parent.getChildAdapterPosition(view) == 0)
            return;*/

        outRect.top = mDrawable.getIntrinsicHeight();
    }
}
