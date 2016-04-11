package com.ch.wchhuangya.dzah.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ch.wchhuangya.dzah.android.db.contract.OtherContactsContract;

/**
 * 数据库助手类
 * Created by wchya on 2015-10-08.
 */
public class DZAHDB extends SQLiteOpenHelper {

    /** 数据库版本 */
    private static final int DZH_DB_VERSION = 1;
    /** 数据库名称 */
    private static final String DZH_DB_NAME = "DZH.db";
    /** 数据库的单例 */
    private static DZAHDB instance;

    /**
     * 数据库实例的单例模式
     * @param context 应用上下文
     * @return
     */
    public static synchronized DZAHDB getInstance(Context context) {
        // 使用应用的上下文，这样会确保你不会偶尔的泄漏Activity的上下文
        if (instance == null) {
            instance = new DZAHDB(context.getApplicationContext(), DZH_DB_NAME, null, DZH_DB_VERSION);
        }
        return instance;
    }

    /**
     * 构造器设置为私有属性是为了阻止直接的实例化，促使调用者使用getInstance()的静态方法
     * @param context 应用上下文
     * @param name 数据库名称
     * @param factory 工厂类
     * @param version 数据版本
     */
    private DZAHDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DZH_DB_NAME, null, DZH_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(OTHER_CONTACTS_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(OTHER_CONTACTS_DELETE);
        onCreate(db);
    }

    public void insertContacts() {

    }

    /** 外部联系人表的创建语句 */
    private static final String OTHER_CONTACTS_CREATION = "CREATE TABLE " + OtherContactsContract.TABLE_NAME
            + "(" + OtherContactsContract.OtherContacts._ID + " INTEGER PRIMARY KEY, " + OtherContactsContract.OtherContacts.USER_ID + " TEXT, "
            + OtherContactsContract.OtherContacts.USER_NAME + " TEXT, " + OtherContactsContract.OtherContacts.USER_PHONE + " TEXT, "
            + OtherContactsContract.OtherContacts.USER_DEPT + " TEXT)";
    /** 外部联系人表的删除语句 */
    private static final String OTHER_CONTACTS_DELETE = "DROP TABLE IF EXISTS " + OtherContactsContract.TABLE_NAME;
}
