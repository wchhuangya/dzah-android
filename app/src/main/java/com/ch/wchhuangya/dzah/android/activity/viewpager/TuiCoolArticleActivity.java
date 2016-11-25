package com.ch.wchhuangya.dzah.android.activity.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.components.TopBar;

/**
 * Created by wchya on 2016-11-25 10:50
 */

public class TuiCoolArticleActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tui_cool_home);

        ((TopBar) findViewById(R.id.top_bar)).setClickListener(mClickListener);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.tui_cool_home_drawerlayout);
    }

    private TopBar.ClickListener mClickListener = new TopBar.ClickListener() {
        @Override
        public void leftClickListener() {
            finish();
        }

        @Override
        public void rightClickListener() {
            if (!mDrawerLayout.isDrawerOpen(Gravity.LEFT))
                mDrawerLayout.openDrawer(Gravity.LEFT);
        }

        @Override
        public void rightBtnClickListener() {
            showToast("你点击了母鸡");
        }
    };
}
