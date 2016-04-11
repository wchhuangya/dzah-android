package com.ch.wchhuangya.dzah.android.activity.sms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发送短信
 * Created by wchya on 2016-01-03 20:31.
 */
public class SendIntentSmsActivity extends BaseActivity {

    @Bind(R.id.sms_number_et)
    EditText mSmsNumberEt;
    @Bind(R.id.sms_content_et)
    EditText mSmsContentEt;
    @Bind(R.id.send_sms_btn)
    Button mSendSmsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_sms);
        ButterKnife.bind(this);

        activity = this;
    }

    @OnClick(R.id.send_sms_btn)
    public void sendSms(View view) {
        if (mSmsNumberEt.getText().toString().trim().equals(""))
            Toast.makeText(activity, "请输入接收号码!", Toast.LENGTH_LONG).show();
        else if (mSmsContentEt.getText().toString().trim().equals(""))
            Toast.makeText(activity, "请输入短信内容!", Toast.LENGTH_LONG).show();
        else {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            //intent.setType(HTTP.PLAIN_TEXT_TYPE); //这样可能会打开其它的非短信应用
            intent.setData(Uri.parse("smsto:" + mSmsNumberEt.getText().toString().trim())); // 这样可以确保打开短信应用
            intent.putExtra("sms_body", mSmsContentEt.getText().toString().trim());
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
            else
                Toast.makeText(activity, "没有可以处理该动作的应用!", Toast.LENGTH_LONG).show();
        }
    }
}
