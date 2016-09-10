package com.ch.wchhuangya.dzah.android.activity.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

/**
 * 自定义视图测试/复习类
 * Created by wchya on 16/9/9.
 */
public class TestCustomViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_custom_view);
    }
}
