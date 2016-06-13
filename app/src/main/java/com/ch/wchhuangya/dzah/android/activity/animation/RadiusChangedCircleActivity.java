package com.ch.wchhuangya.dzah.android.activity.animation;

import android.os.Bundle;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.components.DrawRadiusChangedCircle;

/**
 * 演示自定义控件（半径变化的圆）
 * Created by wchya on 2016/6/8.
 */
public class RadiusChangedCircleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radius_changed_cicrcle);

        DrawRadiusChangedCircle circle = (DrawRadiusChangedCircle) findViewById(R.id.radius_changed_circle_circle);
        new Thread(circle).start();
    }
}
