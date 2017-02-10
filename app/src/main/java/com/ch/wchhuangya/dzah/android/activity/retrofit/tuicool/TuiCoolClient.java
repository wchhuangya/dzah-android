package com.ch.wchhuangya.dzah.android.activity.retrofit.tuicool;

import android.text.TextUtils;

import com.ch.wchhuangya.dzah.android.activity.retrofit.ServiceGenerate;
import com.ch.wchhuangya.dzah.android.interfaces.ResponseComplete;
import com.ch.wchhuangya.dzah.android.interfaces.ResponseError;
import com.ch.wchhuangya.dzah.android.interfaces.ResponseSuccess;
import com.ch.wchhuangya.dzah.android.model.Article;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wchya on 2016-12-06 09:36
 */

public class TuiCoolClient {
    /**
     * 获取文章列表
     * @param size 数据条数
     * @param lang 语言
     * @param cid 类别
     * @param lastId 最后一条记录 id，加载更多时用
     * @param pageNo 要请求的页数，加载更多时用
     * @param firstTime 上次请求时间，下拉刷新时用
     * @param firstId 上次请求返回结果中第一条数据的 id，下拉刷新时用
     * @param success 请求成功调用的接口
     * @param error 请求失败调用的接口
     */
    public static void article (String size, String lang, String cid, String lastId, String pageNo,
                                long firstTime, String firstId, ResponseSuccess<Article> success, ResponseError error, ResponseComplete complete) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("size", size);
        queryMap.put("lang", lang);
        queryMap.put("cid", cid);
        queryMap.put("is_pad", "1");
        if (!TextUtils.isEmpty(lastId))
            queryMap.put("last_id", lastId);
        if (!TextUtils.isEmpty(pageNo))
            queryMap.put("pn", pageNo);
        if (firstTime != -1)
            queryMap.put("first_time", firstTime + "");
        if (!TextUtils.isEmpty(firstId))
            queryMap.put("first_id", firstId);

        ServiceGenerate.createService(ArticleService.class).article(queryMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success::onSuccess, error::onError, complete::onComplete);
    }
}
