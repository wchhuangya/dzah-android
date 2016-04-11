package com.ch.wchhuangya.dzah.android.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences助手类
 * Created by wchya on 2015-08-17.
 */
public class SharedPreferencesHelper {

    /**
     * 根据SP文件名称、key值获取相应的字符
     * @param context 应用上下文
     * @param filename SP文件名称
     * @param key 要获取值的key
     * @return 如果文件或key不存在，返回null，其余情况返回值
     */
    public static String getString(Context context, String filename, String key) {
        SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);

        return sp.getString(key, null);
    }

    /**
     * 根据SP文件名称、key值获取相应的整形
     * @param context 应用上下文
     * @param filename SP文件名称
     * @param key 要获取值的key
     * @return 如果文件或key不存在，返回Constant.DEFAULT_INT，其余情况返回值
     */
    public static int getInt(Context context, String filename, String key) {
        SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);

        return sp.getInt(key, Constant.DEFAULT_INT);
    }

    /**
     * 根据SP文件名称、key值获取相应的整形
     * @param context 应用上下文
     * @param filename SP文件名称
     * @param key 要获取值的key
     * @return 如果文件或key不存在，返回false，其余情况返回值
     */
    public static boolean getBool(Context context, String filename, String key) {
        SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);

        return sp.getBoolean(key, Constant.DEFAULT_BOOL);
    }
}
