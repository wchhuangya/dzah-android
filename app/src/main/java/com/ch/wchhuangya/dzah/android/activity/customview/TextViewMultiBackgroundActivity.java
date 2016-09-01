package com.ch.wchhuangya.dzah.android.activity.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

/**
 * 自定义有双框的 TextView
 * Created by wchya on 16/9/1.
 */
public class TextViewMultiBackgroundActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview_multi_backgrounds);
    }
}
