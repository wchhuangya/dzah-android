package com.ch.wchhuangya.dzah.android.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * 电话数据库
 * Created by wchya on 16/9/24.
 */

public class PhoneDB extends SQLiteOpenHelper {
    /** 数据库名称 */
    public static final String DB_NAME = "phone.db";
    /** 数据库版本 */
    private static final int DB_VERSION = 2;
    /** 电话数据库实例 */
    private static PhoneDB mInstance;
    /** 呼叫记录表 */
    public static final String TABLE_CALLS_RECORD_NAME = "CALLS_RECORD";
    /** 数据库管理实例 */
    private static SQLiteDatabase mDb;

    private PhoneDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CallsRecord.CREATE_TAB_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 数据库升级时的操作
        // 第一次升级数据库,修改数据库字段 PHONE_NUMBER, 取消 NOT NULL
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALLS_RECORD_NAME);
        onCreate(db);
    }

    /** 电话数据库单例获取 */
    public static PhoneDB getInstance(Context context) {
        synchronized (PhoneDB.class) {
            if (mInstance == null) {
                mInstance = new PhoneDB(context.getApplicationContext(), DB_NAME, null, DB_VERSION);
                mDb = mInstance.getWritableDatabase();
            }
        }

        return mInstance;
    }

    /** 呼叫记录 */
    public static abstract class CallsRecord implements BaseColumns {
        /** 电话号码 */
        public static final String PHONE_NUMBER = "PHONE_NUMBER";
        /** 发生的日期、时间,友好格式 */
        public static final String DATE_TIME = "DATE_TIME";
        /** 发生的日期、时间,毫秒格式 */
        public static final String DATE_TIME_MSEC = "DATE_TIME_MSEC";
        /** 动作类型,具体分类值请参见 PhoneDB.PhoneType 实体 */
        public static final String TYPE = "TYPE";

        /** 创建表的 SQL 语句 */
        private static final String CREATE_TAB_SQL = "CREATE TABLE [" + PhoneDB.TABLE_CALLS_RECORD_NAME
                + "] (" + _ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + PHONE_NUMBER
                + " TEXT NOT NULL, " + DATE_TIME + " TEXT NOT NULL, " + DATE_TIME_MSEC + " INTEGER NOT NULL, " + TYPE + " INTEGER NOT NULL)";
    }

    public enum PhoneType {
        TYPE_IDEL(0, "CALL_STATE_IDLE"),
        TYPE_RINGING(1, "CALL_STATE_RINGING"),
        TYPE_OFFHOOK(2, "CALL_STATE_OFFHOOK");

        private int value;
        private String describe;

        PhoneType (int value, String describe) {
            this.value = value;
            this.describe = describe;
        }

        /** 根据 value 返回对应的 describe */
        public static String getDescrie(PhoneType phoneType) {
            for (PhoneType type : PhoneType.values()) {
                if (type == phoneType)
                    return type.describe;
            }
            return PhoneType.getDescrie(PhoneType.TYPE_IDEL);
        }
    }

    /**
     * 插入呼叫信息
     * @param phoneNumber 呼入/出的电话号码
     * @param dateTime 时间,格式 yyyy-MM-dd HH:mm:ss
     * @param dateTimeMsec 时间,格式 毫秒
     * @param type 类型,取值范围及值参见 PhoneType 类
     */
    public void insertRecord(String phoneNumber, String dateTime, long dateTimeMsec, String type) {
        String sql = "insert into " + TABLE_CALLS_RECORD_NAME + "(" + CallsRecord.PHONE_NUMBER + ", " + CallsRecord.DATE_TIME
                        + ", " + CallsRecord.DATE_TIME_MSEC + ", " + CallsRecord.TYPE + ") values('" + phoneNumber
                        + "', '" + dateTime + "', '" + dateTimeMsec + "', '" + type + "')";
        mDb.execSQL(sql);
    }

    /**
     * 是否需要保存记录
     * @param phoneNumber 拨打/接听的电话号码(可能为空)
     * @param dateTimeMsec 动作发生的时间戳
     * @param type 动作类型
     */
    public boolean needSave(String phoneNumber, long dateTimeMsec, String type) {
        String sql = "select " + CallsRecord._ID + " from " + TABLE_CALLS_RECORD_NAME
                + " where " + CallsRecord.DATE_TIME_MSEC + " - " + dateTimeMsec + " < 2 and " + CallsRecord.TYPE + " = '" + type
                + "' and " + CallsRecord.PHONE_NUMBER + " = '" + phoneNumber + "'";
        Cursor cursor = mDb.rawQuery(sql, null);
        return cursor.getCount() == 0;
    }

    /**
     * 查找所有的记录
     * @param column 要排序的列名
     * @param order 排序的规则
     */
    public Cursor findAll(String column, String order) {
        String sql = "select * from " + TABLE_CALLS_RECORD_NAME + " order by " + column + " " + order;
        return mDb.rawQuery(sql, null);
    }
}
