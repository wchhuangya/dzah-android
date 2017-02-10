package com.ch.wchhuangya.dzah.android.activity.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit Service 生成类
 * Created by wchya on 16/11/1.
 */

public class ServiceGenerate {

    // GitHub 基本网址: https://api.github.com/
    // 知乎基本网址：http://news-at.zhihu.com/
    public static final String BASE_URL = "http://api.tuicool.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    /** 根据传入的接口转换为 Retrofit<T/> 的类型 */
    public static <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(serviceClass);
    }
}
