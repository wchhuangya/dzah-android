package com.ch.wchhuangya.dzah.android.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ch.wchhuangya.dzah.android.db.contract.MessageContract;

/**
 * 短信数据库
 * Created by wchya on 16/9/12.
 */
public class MessageDB extends SQLiteOpenHelper {

    /** 数据库版本 */
    private static final int MESSAGE_DB_VERSION = 1;
    /** 数据库名称 */
    private static final String MESSAGE_DB_NAME = "MESSAGE.db";
    /** 数据库的单例 */
    private static MessageDB instance;
    /** 数据库操作实例 */
    private static SQLiteDatabase database;

    /**
     * 数据库实例的单例模式
     * @param context 应用上下文
     * @return
     */
    public static synchronized MessageDB getInstance(Context context) {
        // 使用应用的上下文，这样会确保你不会偶尔的泄漏Activity的上下文
        if (instance == null) {
            instance = new MessageDB(context.getApplicationContext(), MESSAGE_DB_NAME, null, MESSAGE_DB_VERSION);
            database = instance.getWritableDatabase();
        }
        return instance;
    }

    private MessageDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MESSAGE_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(MESSAGE_DELETE);
        onCreate(sqLiteDatabase);
    }

    /** 符合条件的记录是否存在 */
    public boolean recordExist(String address, String content, String time) {
        String sql = "select * from " + MessageContract.TABLE_NAME + " where " + MessageContract.Message.ADDRESS + " =? and "
                            + MessageContract.Message.CONTENT + " =? and " + MessageContract.Message.TIME + " =?";
        Cursor cursor = MessageDB.database.rawQuery(sql, new String[]{address, content, time});
        return cursor.getCount() > 0;
    }

    /** 插入短信 */
    public void insertData(String address, String content, String time) {
        String sql = "insert into " + MessageContract.TABLE_NAME + "(" + MessageContract.Message.ADDRESS + "," + MessageContract.Message.CONTENT
                        + "," + MessageContract.Message.TIME + ") values(?,?,?)";

        database.beginTransaction(); // 手动设置开始事务

        database.execSQL(sql, new String[]{address, content, time});

        database.setTransactionSuccessful(); // 设置事务处理成功，不设置会自动回滚不提交
        database.endTransaction(); // 处理完成
    }

    /** 外部联系人表的创建语句 */
    private static final String MESSAGE_CREATION = "CREATE TABLE " + MessageContract.TABLE_NAME
            + "([" + MessageContract.Message._ID + "]  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, [" + MessageContract.Message.ADDRESS + "] TEXT, ["
            + MessageContract.Message.CONTENT + "] TEXT, [" + MessageContract.Message.TIME + "] TEXT)";
    /** 外部联系人表的删除语句 */
    private static final String MESSAGE_DELETE = "DROP TABLE IF EXISTS " + MessageContract.TABLE_NAME;
}
