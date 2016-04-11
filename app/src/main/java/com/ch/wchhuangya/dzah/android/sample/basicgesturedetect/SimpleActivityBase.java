package com.ch.wchhuangya.dzah.android.sample.basicgesturedetect;

import android.os.Bundle;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.util.logger.Log;
import com.ch.wchhuangya.dzah.android.util.logger.LogWrapper;

/**
 * 回本的启动activity，用于处理示例中最觉的管道
 * Created by wchya on 2015-10-13.
 */
public class SimpleActivityBase extends BaseActivity {

    public static final String TAG = "SampleActivityBase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected  void onStart() {
        super.onStart();
        initializeLogging();
    }

    /** 设置接收日志数据的目标 */
    public void initializeLogging() {
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        // Wraps Android's native log framework
        LogWrapper logWrapper = new LogWrapper();
        Log.setLogNode(logWrapper);

        Log.i(TAG, "Ready");
    }
}
