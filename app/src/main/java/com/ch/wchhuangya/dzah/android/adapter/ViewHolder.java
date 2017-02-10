package com.ch.wchhuangya.dzah.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.R;

/**
 * Created by wchya on 2016-12-06 09:41
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageview;
    public TextView mTitleTv;
    public TextView mFeedTv;
    public TextView mTimeTv;

    public ViewHolder(View itemView) {
        super(itemView);

        mImageview = (ImageView) itemView.findViewById(R.id.article_item_iv);
        mTitleTv = (TextView) itemView.findViewById(R.id.article_item_title_tv);
        mFeedTv = (TextView) itemView.findViewById(R.id.article_item_feed_tv);
        mTimeTv = (TextView) itemView.findViewById(R.id.article_item_time_tv);
    }
}
