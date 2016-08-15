package com.ch.wchhuangya.dzah.android.activity.retrofit.getipins;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工厂类
 * Created by wchya on 16/8/11.
 */
public class ServerFactory {

    /**
     * 返回由serverClass定义的API端点的实现
     * @param serverClass 定论API端点的接口
     * @param url 基地址
     * @param <T> 泛型
     */
    public static <T> T createServiceFactory(final Class<T> serverClass, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serverClass);
    }
}
