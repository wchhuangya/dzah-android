package com.ch.wchhuangya.dzah.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.FileHelper;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;

/**
 * 短信监听器
 * Created by wchya on 2016-01-08 9:30.
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constant.ACTION_HANDLE_SMS_RECEIVER)) {
            FileHelper.createFile(FileHelper.DZAH_LOG_DOR,
                    TimeHelper.getCurrentTime(null).replaceAll(" ", "_").replaceAll(":", "_").replaceAll("-", "_") + ".log", intent.getStringExtra("content"));
        }
    }
}
