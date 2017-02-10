package com.ch.wchhuangya.dzah.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;

/**
 * Created by wchya on 2017-02-06 18:01
 */

public class StartService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        // 启动服务时，最先执行的方法
        // 如果服务已经处于启动状态，再次调用 startService() 方法时不会进入该方法
        // 用于做一些准备工作

        Log.d(Constant.DZAH_TAG, "服务创建了: onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constant.DZAH_TAG, "传入的 startId: " + startId);
        Log.d(Constant.DZAH_TAG, "开始做耗时操作～" + TimeHelper.getCurrentTime("HH:mm:ss"));

        //======================= 第一种方式：会将界面卡死，原因是服务默认运行在宿主程序所在的主线程中，如果进行耗时操作，会将主线程（UI 线程）卡死====================
        /*Log.d(Constant.DZAH_TAG, "操作在: " + Thread.currentThread().getName() + " 线程中执行~" + TimeHelper.getCurrentTime("HH:mm:ss"));
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Log.d(Constant.DZAH_TAG, "线程休息时出问题了");
        }
        Log.d(Constant.DZAH_TAG, "操作完成～" + TimeHelper.getCurrentTime("HH:mm:ss"));*/

        //======================= 第二种方式：在服务中新开线程运行耗时操作，程序运行良好============================
        new Thread(() -> {
            Log.d(Constant.DZAH_TAG, "操作在: " + Thread.currentThread().getName() + " 线程中执行~" + TimeHelper.getCurrentTime("HH:mm:ss"));
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                Log.d(Constant.DZAH_TAG, "线程休息时出问题了");
            }
            Log.d(Constant.DZAH_TAG, "操作完成～" + TimeHelper.getCurrentTime("HH:mm:ss"));

            // 当该服务被调用 4 次后，停止服务
            stopSelfResult(4);
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 启动的服务
        // 如果不允许绑定，直接返回 null
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constant.DZAH_TAG, "服务被销毁~" + TimeHelper.getCurrentTime("HH:mm:ss"));
    }
}
