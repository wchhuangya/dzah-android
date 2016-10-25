package com.ch.wchhuangya.dzah.android.util;

import android.provider.CallLog;

/**
 * 字符串助手类
 * Created by wchya on 16/10/17.
 */

public class StringHelper {

    private StringHelper() {
        throw new UnsupportedOperationException("该类不能被实例化!");
    }

    /**
     * 根据 Calls 表中的 type 和 duration 来获取电话的文字说明
     * @param type 通话类型，1-呼入，2-呼出，3-未接
     * @param duration 通话时长
     */
    public static String getCallType(int type, int duration) {
        switch (type) {
            case CallLog.Calls.INCOMING_TYPE:
                if (duration == 0)
                    return "未接";
                else
                    return "呼入" + TimeHelper.getFriendlyTime(duration);
            case CallLog.Calls.OUTGOING_TYPE:
                if (duration == 0)
                    return "呼出";
                else
                    return "呼出" + TimeHelper.getFriendlyTime(duration);
            case CallLog.Calls.MISSED_TYPE:
                return "未接";
        }
        return "未知";
    }
}
