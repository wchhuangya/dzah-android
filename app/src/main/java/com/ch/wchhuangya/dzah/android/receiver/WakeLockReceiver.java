package com.ch.wchhuangya.dzah.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ch.wchhuangya.dzah.android.service.CallsService;
import com.ch.wchhuangya.dzah.android.service.SmsService;
import com.ch.wchhuangya.dzah.android.util.ActivityHelper;

/**
 * 唤醒状态接收器
 * Created by wchya on 2016-01-10 20:37.
 */
public class WakeLockReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            if (!ActivityHelper.isServiceRunning(context, SmsService.class.getName())) {
                Intent i = new Intent(context, SmsService.class);
                context.startService(i);
            }
            if (!ActivityHelper.isServiceRunning(context, CallsService.class.getName())) {
                Intent i = new Intent(context, CallsService.class);
                context.startService(i);
            }
        }
    }
}
