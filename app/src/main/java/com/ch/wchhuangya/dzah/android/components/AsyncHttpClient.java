package com.ch.wchhuangya.dzah.android.components;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.DataAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * AsyncHttpClient助手类
 * Created by wchya on 2015-10-08.
 */
public class AsyncHttpClient {
    /** 网络请求的客户端 */
    private static com.loopj.android.http.AsyncHttpClient client = new com.loopj.android.http.AsyncHttpClient();
    /** 基本网址 */
    private static final String BASE_URL = "";
    /** 连接超时时间 */
    private static final int TIME_OUT = 10000;
    /** 响应超时时间。调用者的请求如果耗时较长，可以在调用的地方修改响应超时的时间 */
    public static int RESPONSE_TIME_OUT = 10000;

    static {
        client.setConnectTimeout(TIME_OUT);
        client.setResponseTimeout(RESPONSE_TIME_OUT);
    }

    /**
     * 根据URL、请求参数、和结果处理的句柄来进行get请求（改变URL参数的值，添加基本的访问路径）
     * @param url 请求的URL
     * @param params 请求参数
     * @param responseHandler 结果处理句柄
     */
    public static void get(String url, boolean isAppendUrl, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(getUrl(url, isAppendUrl), params, responseHandler);
    }

    /**
     * 根据URL、请求参数、和结果处理的句柄来进行get请求
     * @param url 请求的URL
     * @param isAppendUrl 是否添加基本地址
     * @param params 请求参数
     * @param responseHandler 结果处理句柄
     */
    public static void get(String url, boolean isAppendUrl, RequestParams params, JsonHttpResponseHandler responseHandler){
        client.get(getUrl(url, isAppendUrl), params, responseHandler);
    }

    /**
     * 根据URL、请求参数、和结果处理的句柄来进行get请求
     * @param url 请求的URL
     * @param isAppendUrl 是否添加基本地址
     * @param params 请求参数
     * @param responseHandler 结果处理句柄
     */
    public static void get(String url, boolean isAppendUrl, RequestParams params, TextHttpResponseHandler responseHandler){
        client.get(getUrl(url, isAppendUrl), params, responseHandler);
    }

    /**
     * 根据URL、请求参数、和结果处理的句柄来进行post请求
     * @param url 请求的URL
     * @param isAppendUrl 是否添加基本地址
     * @param params 请求参数
     * @param responseHandler 结果处理句柄
     */
    public static void post(String url, boolean isAppendUrl, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getUrl(url, isAppendUrl), params, responseHandler);
    }

    /**
     * 根据URL、请求参数、和结果处理的句柄来进行post请求
     * @param url 请求的URL
     * @param isAppendUrl 是否添加基本地址
     * @param params 请求参数
     * @param responseHandler 结果处理句柄
     */
    public static void post(String url, boolean isAppendUrl, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.get(getUrl(url, isAppendUrl), params, responseHandler);
    }

    /**
     * 根据URL、请求参数、和结果处理的句柄来进行post请求
     * @param url 请求的URL
     * @param isAppendUrl 是否添加基本地址
     * @param params 请求参数
     * @param responseHandler 结果处理句柄
     */
    public static void post(String url, boolean isAppendUrl, RequestParams params, TextHttpResponseHandler responseHandler) {
        client.get(getUrl(url, isAppendUrl), params, responseHandler);
    }

    /**
     * 根据URL、请求参数、和结果处理的句柄来进行post请求
     * @param url 请求的URL
     * @param isAppendUrl 是否添加基本地址
     * @param params 请求参数
     * @param responseHandler 结果处理句柄
     */
    public static void post(String url, boolean isAppendUrl, RequestParams params, DataAsyncHttpResponseHandler responseHandler) {
        client.get(getUrl(url, isAppendUrl), params, responseHandler);
    }

    /**
     * 返回最终网址
     * @param url
     * @param isAppend
     * @return
     */
    private static String getUrl(String url, boolean isAppend) {
        if(isAppend)
            return BASE_URL + url;
        else
            return url;
    }

    public static void setResponseTimeOut(int responseTimeOut) {
        RESPONSE_TIME_OUT = responseTimeOut;
    }
}
