package com.ch.wchhuangya.dzah.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;

/**
 * Created by wchya on 2017-02-10 14:22
 */

public class BindServiceBinderService extends Service {

    public class MyBinder extends Binder {
        public BindServiceBinderService getService() {
            return BindServiceBinderService.this;
        }
    }

    private IBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constant.DZAH_TAG, "开始绑定: " + TimeHelper.getCurrentTime("HH:mm"));
        return myBinder;
    }

    public String printInfo() {
        return "这是来自于 BindServiceBinderService 的信息";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constant.DZAH_TAG, "绑定服务创建: " + TimeHelper.getCurrentTime("HH:mm"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constant.DZAH_TAG, "进入 onStartCommand 方法（不可能）: " + TimeHelper.getCurrentTime("HH:mm"));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constant.DZAH_TAG, "开始销毁: " + TimeHelper.getCurrentTime("HH:mm"));
    }
}
