package com.ch.wchhuangya.dzah.android.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.ch.wchhuangya.dzah.android.activity.service.StartServiceActivity;
import com.ch.wchhuangya.dzah.android.util.Constant;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by wchya on 2017-02-07 14:42
 */

public class StartIntentService extends IntentService {

    private Intent mIntent;

    public StartIntentService() {
        super("IntentServiceExcise");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constant.DZAH_TAG, "进入了 IntentService 的 onCreate 方法～");
        mIntent = new Intent(StartServiceActivity.ACTIVITY_SERVICE_INTERACTIVE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constant.DZAH_TAG, "进入了 IntentService 的 onStartCommand 方法～");
        Log.d(Constant.DZAH_TAG, "startId 为：" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
       /* Log.d(Constant.DZAH_TAG, "操作在: " + Thread.currentThread().getName() + " 线程中执行~" + TimeHelper.getCurrentTime("HH:mm:ss"));
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Log.d(Constant.DZAH_TAG, "线程休息时出问题了");
        }
        Log.d(Constant.DZAH_TAG, "操作完成～" + TimeHelper.getCurrentTime("HH:mm:ss"));*/

        Observable
                .interval(0, 100, TimeUnit.MILLISECONDS)
//                .range(0, 300)
                .filter(integer -> {
                    return (integer < 300) && (integer % 3 == 0);
                })
                .subscribe(integer -> {
                    mIntent.putExtra("num", integer);
                    sendBroadcast(mIntent);
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constant.DZAH_TAG, "进入了 IntentService 的 onDestroy 方法～");
    }
}
