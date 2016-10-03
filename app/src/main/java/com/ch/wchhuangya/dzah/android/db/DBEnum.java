package com.ch.wchhuangya.dzah.android.db;

/**
 * 数据库的公用接口
 * Created by wchya on 16/10/2.
 */

public enum DBEnum {
    DB_SORT_ASC("ASC"),
    DB_SORT_DESC("DESC");

    private String order;

    DBEnum(String order) {
        this.order = order;
    }

    public static String getOrder(DBEnum dbEnum) {
        for (DBEnum dbEnum1 : DBEnum.values()) {
            if (dbEnum1 == dbEnum)
                return dbEnum1.order;
        }
        return DBEnum.getOrder(DBEnum.DB_SORT_ASC);
    }
}
