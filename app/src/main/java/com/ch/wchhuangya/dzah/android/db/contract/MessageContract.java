package com.ch.wchhuangya.dzah.android.db.contract;

import android.provider.BaseColumns;

/**
 * 短信契约
 * Created by wchya on 16/9/12.
 */
public class MessageContract {

    // 为了防止其它人因为误操作而实例化该类，给该类一个空的构造器
    public MessageContract(){}

    /** 短信表名 */
    public static final String TABLE_NAME = "MESSAGE";

    /** 短信表的列名 */
    public static abstract class Message implements BaseColumns {

        // 不能被实例化
        private Message(){}
        /** 发件人/收件人号码 */
        public static final String ADDRESS = "ADDRESS";
        /** 短信内容 */
        public static final String CONTENT = "CONTENT";
        /** 接收时间 */
        public static final String TIME = "TIME";
    }
}
