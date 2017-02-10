package com.ch.wchhuangya.dzah.android.interfaces;


/**
 * Created by wchya on 2016-11-18 15:49
 */
public interface ResponseSuccess<T> {

    /** 请求成功的响应方法 */
    void onSuccess(T t);
}
