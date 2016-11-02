package com.ch.wchhuangya.dzah.android.activity.retrofit.zhihu;

import com.ch.wchhuangya.dzah.android.model.ZhiHuLatest;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 知乎客户端接口
 * Created by wchya on 16/11/1.
 */

public interface ZhiHuClient {

    /** 获取知乎最新消息 */
    @GET("api/4/news/latest")
    Observable<ZhiHuLatest> latest();
}
