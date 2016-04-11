package com.ch.wchhuangya.dzah.android.db.contract;

import android.provider.BaseColumns;

/**
 * 外部联系人契约类
 * Created by wchya on 2015-10-08.
 */
public final class OtherContactsContract {

    // 为了防止其它人因为误操作而实例化该类，给该类一个空的构造器
    public OtherContactsContract(){}

    /** 外部联系人表名 */
    public static final String TABLE_NAME = "OTHER_CONTACTS";

    /** 外部联系人表的列名 */
    public static abstract class OtherContacts implements BaseColumns {

        // 不能被实例化
        private OtherContacts(){}
        /** 用户ID */
        public static final String USER_ID = "USER_ID";
        /** 用户姓名 */
        public static final String USER_NAME = "USER_NNAME";
        /** 用户号码 */
        public static final String USER_PHONE = "USER_PHONE";
        /** 用户部门 */
        public static final String USER_DEPT = "USER_DEPT";
    }
}
