package com.ch.wchhuangya.dzah.android.activity.retrofit.tuicool;


import com.ch.wchhuangya.dzah.android.model.Article;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by wchya on 2016-11-18 14:27
 */

public interface ArticleService {

    /**
     * 获取文章列表的方法
     * @param queryMap 查询参数
     */
    @GET("/api/articles/hot.json")
    @Headers({"Authorization: Basic MTAuMC4zLjE1OnR1aWNvb2w="})
    Observable<Article> article(@QueryMap Map<String, String> queryMap);
}
