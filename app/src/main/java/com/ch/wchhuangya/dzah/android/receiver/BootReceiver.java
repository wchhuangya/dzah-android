package com.ch.wchhuangya.dzah.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ch.wchhuangya.dzah.android.service.CallsService;
import com.ch.wchhuangya.dzah.android.service.SmsService;

/**
 * 接收开机广播
 * Created by wchya on 16/10/16.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent intent1 = new Intent(context.getApplicationContext(), CallsService.class);
            context.startService(intent1);
            intent1 = new Intent(context.getApplicationContext(), SmsService.class);
            context.startActivity(intent1);
        }
    }
}
