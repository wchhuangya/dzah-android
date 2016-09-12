package com.ch.wchhuangya.dzah.android.activity.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

/**
 * 自定义 View —— 仿音频条状图
 * Created by wchya on 16/9/10.
 */
public class AudioBarChartActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_bar_chart);
    }
}
