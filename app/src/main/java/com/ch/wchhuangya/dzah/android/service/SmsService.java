package com.ch.wchhuangya.dzah.android.service;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.dzah.android.activity.contentobserver.SmsCO;
import com.ch.wchhuangya.dzah.android.model.Sms;

/**
 * 短信服务
 * Created by wchya on 2016-01-08 11:47.
 */
public class SmsService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SmsCO smsCO = new SmsCO(getApplicationContext(), null);
        Uri uri = Uri.parse(Sms.URI_ALL);
        getApplicationContext().getContentResolver().registerContentObserver(uri, true, smsCO);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
