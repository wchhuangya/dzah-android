package com.ch.wchhuangya.dzah.android.activity.contentobserver;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import com.ch.wchhuangya.dzah.android.domain.Sms;
import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.LogHelper;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;

/**
 * 短信内容观察者
 * Created by wchya on 2016-01-07 21:45.
 */
public class SmsCO extends ContentObserver {
    private Context mContext;
    private Handler mHandler;

    /**
     * Creates a content observer.
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public SmsCO(Context context, Handler handler) {
        super(handler);
        mContext = context;
        mHandler = handler;
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
            if (mCursor.moveToNext()) {
                String type = Sms.typeToString(mCursor.getInt(mCursor.getColumnIndex(Sms.FIELD_TYPE)));
                String date = type.equals("发件箱") || type.equals("已发送")
                        ? "【发送时间】" + TimeHelper.changeTimestampToTime(mCursor.getLong(mCursor.getColumnIndex(Sms.FIELD_DATE)), null)
                        : "【接收时间】" + TimeHelper.changeTimestampToTime(mCursor.getLong(mCursor.getColumnIndex(Sms.FIELD_DATE)), null);
                String dateSent = type.equals("发件箱") || type.equals("已发送")
                        ? "【接收时间】" + TimeHelper.changeTimestampToTime(mCursor.getLong(mCursor.getColumnIndex(Sms.FIELD_DATE_SENT)), null)
                        : "【发送时间】" + TimeHelper.changeTimestampToTime(mCursor.getLong(mCursor.getColumnIndex(Sms.FIELD_DATE_SENT)), null);
                String s = "【" + type + "】        "
                        + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_ADDRESS)) + "："
                        + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_BODY))
                        + "        【是否已读：" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_READ)) + "】"
                        + "        【是否看到：" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_SEEN)) + "】"
                        + "        【locked：" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_LOCKED)) + "】"
                        + date + dateSent + "】" + "|||" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_ID));
                LogHelper.e(SmsCO.class, s);
                Intent intent = new Intent();
                intent.setAction(Constant.ACTION_HANDLE_SMS_RECEIVER);
                intent.putExtra("content", s);
                mContext.sendBroadcast(intent);
            }
        }
    }
}
