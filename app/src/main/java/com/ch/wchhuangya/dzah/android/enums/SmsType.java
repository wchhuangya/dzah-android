package com.ch.wchhuangya.dzah.android.enums;

/**
 * 短信类型
 * Created by wchya on 16/10/4.
 */

public enum SmsType {
    TYPE_ALL("全部"),
    TYPE_INBOX("收件箱"),
    TYPE_SENT("已发送"),
    TYPE_DRAFT("草稿箱"),
    TYPE_OUTBOX("发件箱"),
    TYPE_FAILED("发送失败"),
    TYPE_QUEUED("序列中");

    private String value;

    SmsType(String value) {
        this.value = value;
    }

    public static String getValue(int type) {
        for (SmsType smsType : SmsType.values())
            if (type == smsType.ordinal())
                return smsType.value;
        return SmsType.getValue(0);
    }
}
