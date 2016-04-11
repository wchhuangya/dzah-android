package com.ch.wchhuangya.dzah.android.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间助手类
 * Created by wchya on 2015-10-09.
 */
public class TimeHelper {
    private static Calendar calendar;
    private static SimpleDateFormat sdf;
    private static final String DEFAULT_LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_SHORT_FORMAT = "yyyy-MM-dd";

    /**
     * 获取当前时间
     * @param format 时间格式，可以传null，默认格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTime(String format) {
        if(null == format)
            sdf = new SimpleDateFormat(DEFAULT_LONG_FORMAT);
        else
            sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 把时间戳转换为指定的日期
     * @param timestamp 时间戳
     * @param format 要转换的格式，如果传空或null，使用默认长时间格式：yyyy-MM-dd HH:mm:ss
     * @return 转换后的时间
     */
    public static String changeTimestampToTime(long timestamp, String format) {
        if (TextUtils.isEmpty(format))
            sdf = new SimpleDateFormat(DEFAULT_LONG_FORMAT);
        else
            sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(timestamp));
    }

    /**
     * 获取当前时间的时间戳
     * @return 当前时间的时间戳
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
}
