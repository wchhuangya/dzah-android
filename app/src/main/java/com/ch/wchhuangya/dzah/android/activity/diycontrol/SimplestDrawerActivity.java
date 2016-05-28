package com.ch.wchhuangya.dzah.android.activity.diycontrol;

import android.os.Bundle;
import android.view.View;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.components.DiyHorizontalScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 最简单的甜侧滑效果（HorizontalScrollView）
 * Created by wchya on 2016/5/18.
 */
public class SimplestDrawerActivity extends BaseActivity {

    @Bind(R.id.diy_horizontal_scroll_view)
    DiyHorizontalScrollView mDiyHorizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplest_drawer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.switch_btn)
    public void switchLayout(View view) {
        mDiyHorizontalScrollView.changePos();
    }

    @OnClick(R.id.first_menu_tv)
    public void showMsg(View view) {
        showToast("第一个菜单", 1);
    }
}
