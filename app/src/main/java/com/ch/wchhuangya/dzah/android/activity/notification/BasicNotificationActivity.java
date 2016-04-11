package com.ch.wchhuangya.dzah.android.activity.notification;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 处理基本布局通知的点击事件
 * Created by wchya on 2015-10-10.
 */
public class BasicNotificationActivity extends BaseActivity {

    @Bind(R.id.basic_notification_title_tv)
    TextView mTitleTv;
    @Bind(R.id.basic_notification_text_tv)
    TextView mTextTv;
    @Bind(R.id.basic_notification_icon_iv)
    ImageView mIconIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_notification);
        ButterKnife.bind(this);

        activity = this;
        String title = getIntent().getStringExtra("title");
        String text = getIntent().getStringExtra("text");
        int icon = getIntent().getIntExtra("small_icon", -100);

        mTitleTv.setText(title);
        mTextTv.setText(text);
        mIconIv.setImageResource(icon);
    }
}
