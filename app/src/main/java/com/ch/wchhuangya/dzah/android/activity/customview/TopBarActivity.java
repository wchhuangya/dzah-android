package com.ch.wchhuangya.dzah.android.activity.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.components.TopBar;

/**
 * 自定义视力 —— 通用顶部工具栏
 * Created by wchya on 16/9/3.
 */
public class TopBarActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_bar);

        TopBar topBar = (TopBar) findViewById(R.id.top_bar);
        topBar.setClickListener(new TopBar.ClickListener() {
            @Override
            public void leftClickListener() {
                finish();
            }

            @Override
            public void rightClickListener() {
                Toast.makeText(TopBarActivity.this, "你点击了管理!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void rightBtnClickListener() {

            }
        });
    }
}
