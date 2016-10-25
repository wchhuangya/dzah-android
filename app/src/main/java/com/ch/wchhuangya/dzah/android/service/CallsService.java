package com.ch.wchhuangya.dzah.android.service;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.dzah.android.activity.contentobserver.CallsCO;

/**
 * 启动监听电话记录的服务
 * Created by wchya on 16/10/14.
 */

public class CallsService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        CallsCO callsCO = new CallsCO(getApplicationContext(), null);
        Uri uri = Uri.parse("content://call_log/calls");
        getApplicationContext().getContentResolver().registerContentObserver(uri, true, callsCO);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
