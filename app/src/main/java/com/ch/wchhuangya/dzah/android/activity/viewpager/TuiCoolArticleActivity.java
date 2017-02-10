package com.ch.wchhuangya.dzah.android.activity.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.activity.fragment.TuiCoolArticleFragment;
import com.ch.wchhuangya.dzah.android.components.TopBar;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by wchya on 2016-11-25 10:50
 */

public class TuiCoolArticleActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ViewPager mViewpager;
    private SmartTabLayout mTabLayout;

    public static String[] title_arr = {"热门", "推荐", "科技", "创业", "数码", "技术", "设计", "营销"};
    public static String[] cid_arr = {"0", "0", "101000000", "101040000", "101050000", "20", "108000000", "114000000"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tui_cool_home);

        ((TopBar) findViewById(R.id.top_bar)).setClickListener(mClickListener);
        mViewpager = (ViewPager) findViewById(R.id.content_viewpager);
        mViewpager.setOffscreenPageLimit(1);
        mTabLayout = (SmartTabLayout) findViewById(R.id.content_viewpagertab);
        mViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new TuiCoolArticleFragment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title_arr[position];
            }

            @Override
            public int getCount() {
                return title_arr.length;
            }
        });
        mTabLayout.setViewPager(mViewpager);

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
