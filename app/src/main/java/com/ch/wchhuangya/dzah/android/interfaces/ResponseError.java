package com.ch.wchhuangya.dzah.android.interfaces;

/**
 * Created by wchya on 2016-11-18 16:06
 */
public interface ResponseError {

    /** 请求失败的响应方法 */
    void onError(Throwable throwable);
}
