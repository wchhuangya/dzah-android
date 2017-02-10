package com.ch.wchhuangya.dzah.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.model.Article;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wchya on 2016-12-06 09:40
 */

public class ArticleAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Article.ArticlesBean> mData = new ArrayList<>();

    public List<Article.ArticlesBean> getData() {
        return mData;
    }

    public void setData(List<Article.ArticlesBean> data) {
        mData = data;
    }

    public void insertData(List<Article.ArticlesBean> data, int start, int count) {
        mData.addAll(start, data);
        notifyItemRangeInserted(start, count);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tui_cool_article_item, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article.ArticlesBean articlesBean = mData.get(position);
        ImageLoader.getInstance().displayImage(articlesBean.getImg(), holder.mImageview);
        holder.mTitleTv.setText(articlesBean.getTitle());
        holder.mFeedTv.setText(articlesBean.getFeed_title());
        holder.mTimeTv.setText(articlesBean.getTime());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
