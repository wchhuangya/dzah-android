package com.ch.wchhuangya.dzah.android.util;

import android.util.Log;

/**
 * 日志助手类
 * Created by wchya on 2015-08-17.
 */
public class LogHelper {

    /**
     * 打印Debug级别的日志
     * @param msg
     */
    public static void d(Class classs, String msg) {
        if(Constant.DEBUG_SWITCH)
            Log.d(Constant.DZAH_TAG, classs.getSimpleName() + ": " + msg);
    }

    /**
     * 打印Info级别的日志
     * @param msg
     */
    public static void i(Class classs, String msg) {
        if(Constant.DEBUG_SWITCH)
            Log.i(Constant.DZAH_TAG, classs.getSimpleName() + ": " + msg);
    }

    /**
     * 打印Verbose级别的日志
     * @param msg
     */
    public static void v(Class classs, String msg) {
        if(Constant.DEBUG_SWITCH)
            Log.v(Constant.DZAH_TAG, classs.getSimpleName() + ": " + msg);
    }

    /**
     * 打印Error级别的日志
     * @param msg
     */
    public static void w(Class classs, String msg) {
        if(Constant.DEBUG_SWITCH)
            Log.w(Constant.DZAH_TAG, classs.getSimpleName() + ": " + msg);
    }

    /**
     * 打印Error级别的日志
     * @param msg
     */
    public static void e(Class classs, String msg) {
        if(Constant.DEBUG_SWITCH)
            Log.e(Constant.DZAH_TAG, classs.getSimpleName() + ": " + msg);
    }
}
