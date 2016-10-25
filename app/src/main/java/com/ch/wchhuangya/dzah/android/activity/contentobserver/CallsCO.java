package com.ch.wchhuangya.dzah.android.activity.contentobserver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.CallLog;
import android.telephony.TelephonyManager;

import com.ch.wchhuangya.dzah.android.db.PhoneDB;
import com.ch.wchhuangya.dzah.android.util.StringHelper;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;

/**
 * 通话记录内容观察者
 * Created by wchya on 16/10/14.
 */

public class CallsCO extends ContentObserver {
    private Context mContext;
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public CallsCO(Context context, Handler handler) {
        super(handler);
        mContext = context;
    }

    /**
     * 该方法在 API 16 以下调用
     */
    @Override
    public void onChange(boolean selfChange) {
        saveAndSync.start();
    }

    /**
     * 该方法仅仅只能在 API 16 及以上才能被调用
     */
    @Override
    public void onChange(boolean selfChange, Uri uri) {
        saveAndSync.start();
    }

    private Thread saveAndSync = new Thread(new Runnable() {
        @Override
        public void run() {
            Cursor cursor = mContext.getContentResolver().query(Uri.parse("content://call_log/calls"), null, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
            for (int i = 0; i < 1; i++) {
                TelephonyManager manager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);;
                cursor.moveToNext();

                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                int duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
                long datetimeMsec = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
                String datetime = TimeHelper.changeTimestampToTime(datetimeMsec, null);

                PhoneDB.getInstance(mContext).insertRecord(number, datetime, datetimeMsec, StringHelper.getCallType(type, duration),
                        manager.getDeviceId(), Build.MODEL);
            }
        }
    });
}
