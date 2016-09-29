package com.ch.wchhuangya.dzah.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.ch.wchhuangya.dzah.android.db.PhoneDB;
import com.ch.wchhuangya.dzah.android.util.LogHelper;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;

/**
 * 电话相关接收器
 * Created by wchya on 16/9/24.
 */

public class PhoneReceiver extends BroadcastReceiver {
    private TelephonyManager mManager;
    private PhoneDB mPhoneDB;

    @Override
    public void onReceive(Context context, Intent intent) {
        mManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        mPhoneDB = PhoneDB.getInstance(context);
        if ("android.intent.action.NEW_OUTGOING_CALL".equals(intent.getAction())) { // 拨打电话
            LogHelper.i(PhoneReceiver.class, "拨打电话了");
            mManager.listen(new StateListener(), PhoneStateListener.LISTEN_CALL_STATE);
        } else if ("android.intent.action.PHONE_STATE".equals(intent.getAction())) { // 电话状态改变
            LogHelper.i(PhoneReceiver.class, "电话状态变了");
            mManager.listen(new StateListener(), PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    class StateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) { // 呼叫状态改变时执行
            LogHelper.i(PhoneReceiver.class, "电话号码: " + incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE: // 呼叫状态：空闲
                    LogHelper.i(PhoneReceiver.class, "CALL_STATE_IDLE " + TimeHelper.getCurrentTime(null));
                    break;
                case TelephonyManager.CALL_STATE_RINGING: // 呼叫状态：响铃。新的呼叫到达，正在响铃或等待。在等待的状态下，设备上一定已经存在了呼叫
                    LogHelper.i(PhoneReceiver.class, "CALL_STATE_RINGING " + TimeHelper.getCurrentTime(null));
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    LogHelper.i(PhoneReceiver.class, "CALL_STATE_OFFHOOK " + TimeHelper.getCurrentTime(null));
                    break;
            }

            super.onCallStateChanged(state, incomingNumber);
        }
    }

    private class DBThread extends Thread {
        private Context context;
        private String phoneNumber;
        private String dataTime;
        private int dateTimeMsec;
        private int type;

        public DBThread(Context context, String phoneNumber, String dataTime, int dateTimeMsec, int type) {
            this.context = context;
            this.phoneNumber = phoneNumber;
            this.dataTime = dataTime;
            this.dateTimeMsec = dateTimeMsec;
            this.type = type;
        }

        @Override
        public void run() {
            mPhoneDB = PhoneDB.getInstance(context);
        }
    }
}
