package com.ch.wchhuangya.dzah.android.activity.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

/**
 * 自定义控件——文字闪烁
 * Created by wchya on 16/9/1.
 */
public class TextViewFlickerActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview_flicker);
    }
}
