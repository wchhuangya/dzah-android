package com.ch.wchhuangya.dzah.android.model;

import java.io.Serializable;

/**
 * 短信实体
 * Created by wchya on 2016-01-07 19:50.
 */
public class Sms implements Serializable, BaseModel {
    /** 短信内容 */
    public static final String FIELD_BODY = "body";
    /** 发/收件人 */
    public static final String FIELD_ADDRESS = "address";
    /** 类型，具体值见该类的TYPE_变量 */
    public static final String FIELD_TYPE = "type";
    /** 日期，对于收到的短信，是收到时间；对于发送的短信，是发送时间； */
    public static final String FIELD_DATE = "date";
    /** 日期，对于收到的短信，是发送时间；对于发送的短信，是接收时间； */
    public static final String FIELD_DATE_SENT = "date_sent";
    /** Is the message locked？小米notepro上是是否收藏，0-不收藏；1-收藏； */
    public static final String FIELD_LOCKED = "locked";
    /** 用户是否看到，0-未看；1-已看；用于决定是否发送通知； */
    public static final String FIELD_SEEN = "seen";
    /** 用户是否已读，0-未读；1-已读； */
    public static final String FIELD_READ = "read";

    /** 短信类型：全部 */
    public static final int TYPE_ALL = 0;
    /** 短信类型：已收到 */
    public static final int TYPE_INBOX = 1;
    /** 短信类型：已发送 */
    public static final int TYPE_SENT = 2;
    /** 短信类型：草稿 */
    public static final int TYPE_DRAFT = 3;
    /** 短信类型：发件箱 */
    public static final int TYPE_OUTBOX = 4;
    /** 短信类型：发送失败 */
    public static final int TYPE_FAILED = 5;
    /** 短信类型：待发送序列 */
    public static final int TYPE_QUEUED = 6;

    /** 短信Uri */
    public static final String URI_ALL = "content://sms/";
    /** 收件箱Uri */
    public static final String URI_INBOX = "content://sms/inbox";
    /** 已发送Uri */
    public static final String URI_SEND = "content://sms/sent";
    /** 草稿Uri */
    public static final String URI_DRAFT = "content://sms/draft";
    /** 已发送和发送失败的Uri */
    public static final String URI_OUTBOX = "content://sms/outbox";
    /** 发送失败的Uri */
    public static final String URI_FAILED = "content://sms/failed";
    /** 准备待发的Uri */
    public static final String URI_QUEUED = "content://sms/queued";

    public static final String typeToString(int type) {
        String smsType = "";
        switch (type) {
            case Sms.TYPE_ALL:
                smsType = "全部";
                break;
            case Sms.TYPE_INBOX:
                smsType = "收件箱";
                break;
            case Sms.TYPE_SENT:
                smsType = "已发送";
                break;
            case Sms.TYPE_DRAFT:
                smsType = "草稿箱";
                break;
            case Sms.TYPE_OUTBOX:
                smsType = "发件箱";
                break;
            case Sms.TYPE_FAILED:
                smsType = "发送失败";
                break;
            case Sms.TYPE_QUEUED:
                smsType = "序列中";
                break;
        }
        return smsType;
    }
}
