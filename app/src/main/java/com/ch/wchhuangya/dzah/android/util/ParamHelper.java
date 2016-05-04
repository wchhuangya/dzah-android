package com.ch.wchhuangya.dzah.android.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * 参数助手类
 * Created by wchya on 2016/4/21.
 */
public class ParamHelper {

    /**
     * 得到手机分辨率的宽度
     * @return
     */
    public static int getEqumentWidth(Activity activity) {
		/* 必须引用android.util.DisplayMetrics */
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
    /**
     * 得到手机分辨率的高度
     * @return
     */
    public static int getEqumentHeight(Activity activity) {
		/* 必须引用android.util.DisplayMetrics */
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
