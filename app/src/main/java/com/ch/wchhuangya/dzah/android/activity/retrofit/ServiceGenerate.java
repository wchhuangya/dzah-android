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
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    private static Retrofit.Builder noRxBuilder = new Retrofit.Builder();

    /** 根据传入的接口转换为 Retrofit<T/> 的类型 */
    public static <T> T createService(Class<T> serviceClass, int type, String baseUrl) {
        Retrofit retrofit = getBuilder(type)
                                .baseUrl(baseUrl == null ? BASE_URL : baseUrl)
                                .client(httpClient.build())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        return retrofit.create(serviceClass);
    }

    /**
     * 获取 Retrofit.Builder
     * @param type 1-RxJavaBuilder；2-NoRxJavaBuilder；
     */
    private static Retrofit.Builder getBuilder(int type) {
        switch (type) {
            case 1:
                return builder;
            case 2:
                return noRxBuilder;
            default:
                return noRxBuilder;
        }
    }
}
