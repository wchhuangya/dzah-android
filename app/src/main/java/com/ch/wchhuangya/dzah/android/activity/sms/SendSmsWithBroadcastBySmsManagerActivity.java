package com.ch.wchhuangya.dzah.android.activity.sms;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用 SmsManager
 * Created by wchya on 16/9/30.
 */

public class SendSmsWithBroadcastBySmsManagerActivity extends BaseActivity {

    @Bind(R.id.sms_number_et)
    EditText mSmsNumberEt;
    @Bind(R.id.sms_content_et)
    EditText mSmsContentEt;

    private static final String SEND_ACTION = "SEND_ACTION";
    private static final String DELIVERY_ACTION = "DELIVERY_ACTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_sms);
        ButterKnife.bind(this);

        registerReceiver(new BroadcastReceiver() { // 短信发送成功的广播
            @Override
            public void onReceive(Context context, Intent intent) { // 短信发送成功广播
                switch (getResultCode()) {
                    case RESULT_OK:
                        showToast("短信发送成功");
                        break;
                    // 一般错误原因
                    // 可能是接收短信的号码写错,可能是手机没费了,可能是在主线程中给多个号码发送短信,可能是将短信消息服务中心的号码写错
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        showToast("一般错误原因,可能:接收短信的号码写错;手机没费了;在主线程中给多个号码发送短信;将短信消息服务中心的号码写错;");
                        break;
                    // 当前服务不可用
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        showToast("当前服务不可用");
                        break;
                    // 没有提供 PDU 错误
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        showToast("没有提供 PDU 错误");
                        break;
                    // 无线广播被关闭
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        showToast("无线广播被关闭");
                        break;
                }
            }
        }, new IntentFilter(SEND_ACTION));
        registerReceiver(new BroadcastReceiver() { // 接收方收到短信广播
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case RESULT_OK:
                        showToast("接收方已经收到短信");
                        break;
                    case RESULT_CANCELED:
                        showToast("短信没有被投递");
                        break;
                    default:
                        showToast("出了其它的状况");
                        break;
                }
            }
        }, new IntentFilter(DELIVERY_ACTION));
    }

    @OnClick(R.id.send_sms_btn)
    public void sendSms(View view) {
        if ("".equals(mSmsNumberEt.getText().toString().trim()))
            showToast("请输入接收短信的号码!");
        else if ("".equals(mSmsContentEt.getText().toString().trim()))
            showToast("请输入短信内容!");
        else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mSmsNumberEt.getText().toString(), null, mSmsContentEt.getText().toString(),
                    PendingIntent.getBroadcast(activity, 0, new Intent(SEND_ACTION), 0),
                    PendingIntent.getBroadcast(activity, 0, new Intent(DELIVERY_ACTION), 0));
        }
    }
}
