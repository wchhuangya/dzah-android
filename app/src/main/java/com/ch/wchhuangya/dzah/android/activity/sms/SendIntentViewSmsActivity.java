package com.ch.wchhuangya.dzah.android.activity.sms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用 ACTION_VIEW 动作发送 Intent 短信
 * Created by wchya on 16/9/30.
 */

public class SendIntentViewSmsActivity extends BaseActivity {

    @Bind(R.id.sms_number_et)
    EditText mSmsNumberEt;
    @Bind(R.id.sms_content_et)
    EditText mSmsContentEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_sms);
        ButterKnife.bind(this);

        activity = this;
    }

    @OnClick(R.id.send_sms_btn)
    public void sendSms(View view) {
        if ("".equals(mSmsNumberEt.getText().toString().trim()))
            showToast("请输入接收短信的号码!");
        else if ("".equals(mSmsContentEt.getText().toString().trim()))
            showToast("请输入短信内容!");
        else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // TODO 这一句是重点,该句限定了该 Intent 只能由 SMS/MMS 客户端执行
            intent.setType("vnd.android-dir/mms-sms");
            intent.putExtra("address", mSmsNumberEt.getText().toString());
            intent.putExtra("sms_body", mSmsContentEt.getText().toString());
            startActivity(intent);
        }
    }
}
