package com.ch.wchhuangya.dzah.android.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.activity.retrofit.tuicool.TuiCoolClient;
import com.ch.wchhuangya.dzah.android.adapter.ArticleAdapter;
import com.ch.wchhuangya.dzah.android.model.Article;
import com.ch.wchhuangya.dzah.android.util.Constant;

import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by wchya on 2016-12-06 09:24
 */

public class TuiCoolArticleFragment extends Fragment {

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tui_cool_article_fragment, null);
        SwipeRefreshLayout srl = (SwipeRefreshLayout) view.findViewById(R.id.common_refresh_layout);
        RecyclerView recyclerview = (RecyclerView) view.findViewById(R.id.common_recyclerview);
        CircularProgressBar pb = (CircularProgressBar) view.findViewById(R.id.common_pb);

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        ArticleAdapter adapter = new ArticleAdapter();
        recyclerview.setAdapter(adapter);
        TuiCoolClient.article("20", "-1", "0", "", "", -1, "", article -> {
            List<Article.ArticlesBean> articles = article.getArticles();
            adapter.insertData(articles, 0, articles.size());
        }, throwable -> {
            Log.e(Constant.DZAH_TAG, "onCreateView: 出错啦--", throwable);
        }, () -> {

        });
        return view;
    }
}
