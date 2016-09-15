package com.ch.wchhuangya.dzah.android;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

import com.ch.wchhuangya.dzah.android.receiver.WakeLockReceiver;
import com.ch.wchhuangya.dzah.android.service.SmsService;
import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.FileHelper;
import com.tencent.android.tpush.XGPushConfig;

/**
 * Created by wchya on 2015-09-24.
 */
public class DzahApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Bmob.initialize(this, "1ec4aa2ceae3998de0982fe49ff6659e");
        XGPushConfig.enableDebug(this, Constant.DEBUG_SWITCH);

        Intent intent = new Intent(getApplicationContext(), SmsService.class);
        startService(intent);

        WakeLockReceiver receiver = new WakeLockReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiver, filter);

        FileHelper.initDir();
    }
}
