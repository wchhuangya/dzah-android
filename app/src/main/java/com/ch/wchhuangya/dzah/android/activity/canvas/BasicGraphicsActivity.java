package com.ch.wchhuangya.dzah.android.activity.canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.graphics.BasicGraphics;

/**
 * Created by wchya on 2017-02-22 11:51
 */

public class BasicGraphicsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_graphics);

        BasicGraphics bonut = (BasicGraphics) findViewById(R.id.basic_graphics_bonut);
        new Thread(bonut).start();
    }
}
