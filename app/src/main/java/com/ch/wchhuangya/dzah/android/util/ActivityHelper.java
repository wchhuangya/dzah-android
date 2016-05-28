package com.ch.wchhuangya.dzah.android.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.TypedValue;

import java.util.List;

/**
 * Activity助手类
 * Created by wchya on 2016-01-10 20:39.
 */
public class ActivityHelper {

    /**
     * 根据完全的类名判断某个服务是否正在运行
     * @param context 上下文
     * @param className 服务的完全类名
     * @return 服务是否正在运行
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = manager.getRunningServices(40);
        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            if (serviceList.get(i).service.getClassName().equals(className)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 在特定机型上的单位转换
     * @param context 上下文
     * @param unit 要转换的单位，比如：TypeValue.COMPLEX_UNIT_DIP
     * @param value 要转换的值
     * @return
     */
    public static int changeUnit(Context context, int unit, float value) {
        return (int) TypedValue.applyDimension(unit, value, context.getResources().getDisplayMetrics());
    }
}
