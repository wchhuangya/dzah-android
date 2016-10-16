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
        return sdf.format(new Date(timestamp > 999999999999L ? timestamp : timestamp * 1000));
    }

    /**
     * 获取当前时间的时间戳
     * @return 当前时间的时间戳
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 根据秒数返回表示友好的字符串
     * @param second 要格式化的秒数
     */
    public static String getFriendlyTime(int second) {
        if (second < 60)
            return second + "秒";
        else if (second >= 60 && second < 3600)
            return second / 60 + "分" + second % 60 + "秒";
        else
            return second / 3600 + "小时" + getFriendlyTime(second % 3600);
    }
}
