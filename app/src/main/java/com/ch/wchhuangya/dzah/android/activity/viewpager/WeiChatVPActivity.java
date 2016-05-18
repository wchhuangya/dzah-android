package com.ch.wchhuangya.dzah.android.activity.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.LogHelper;
import com.ch.wchhuangya.dzah.android.util.logger.Log;
import com.readystatesoftware.viewbadger.BadgeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 仿微信5.2主界面
 * Created by wchya on 2016/5/4.
 */
public class WeiChatVPActivity extends BaseActivity {

    @Bind(R.id.chat_tv)
    TextView mChatTv;
    @Bind(R.id.find_tv)
    TextView mFindTv;
    @Bind(R.id.contact_tv)
    TextView mContactTv;
    @Bind(R.id.tabline_iv)
    ImageView mTablineIv;
    @Bind(R.id.vp_vp)
    ViewPager mVpVp;

    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mList = new ArrayList<>();
    private int mTablineLength;
    private int mCurIntex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wei_chat_vp);
        ButterKnife.bind(this);

        initTabline();
        init();
    }

    /** 动态获取屏幕1/3的宽度，并把值给标识设置 */
    private void initTabline() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mTablineLength = displayMetrics.widthPixels / 3;
        ViewGroup.LayoutParams params = mTablineIv.getLayoutParams();
        params.width = mTablineLength;
        mTablineIv.setLayoutParams(params);
    }

    @OnClick({R.id.chat_tv, R.id.find_tv, R.id.contact_tv})
    public void tabClick(View view) {
        switch (view.getId()) {
            case R.id.chat_tv:
                if (mCurIntex != 0)
                    mVpVp.setCurrentItem(0, true);
                break;
            case R.id.find_tv:
                if (mCurIntex != 1)
                    mVpVp.setCurrentItem(1, true);
                break;
            case R.id.contact_tv:
                if (mCurIntex != 2)
                    mVpVp.setCurrentItem(2, true);
                break;
        }
    }

    private void init() {
        // 初始化tab的内容视图：三个Fragment
        ChatFragment chatFragment = new ChatFragment();
        FindFragment findFragment = new FindFragment();
        ContactFragment contactFragment = new ContactFragment();

        // 把tab的内容保存到List中
        mList.add(chatFragment);
        mList.add(findFragment);
        mList.add(contactFragment);

        // 设置ViewPager的适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) { // 获取当前位置的Fragment
                return mList.get(position);
            }

            @Override
            public int getCount() { // 获取页面的数目
                return mList.size();
            }
        };

        mVpVp.setAdapter(mAdapter);
        // 设置第一个Tab的标签为选中状态
        mChatTv.setTextColor(getResources().getColor(R.color.vp_selected));
        // 给ViewPager添加页面改变的事件监听
        mVpVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogHelper.d(WeiChatVPActivity.class, position + "            " + positionOffset + "            " + positionOffsetPixels + "            " + mCurIntex);
                LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTablineIv.getLayoutParams();

                if (mCurIntex == 0 && position == 0)// 0->1
                    lp.leftMargin = (int) (positionOffset * mTablineLength + mCurIntex * mTablineLength);
                else if (mCurIntex == 1 && position == 0)// 1->0
                    lp.leftMargin = (int) (mCurIntex * mTablineLength + (positionOffset - 1) * mTablineLength);
                else if (mCurIntex == 1 && position == 1) // 1->2
                    lp.leftMargin = (int) (mCurIntex * mTablineLength + positionOffset * mTablineLength);
                else if (mCurIntex == 2 && position == 1) // 2->1
                    lp.leftMargin = (int) (mCurIntex * mTablineLength + (positionOffset - 1) * mTablineLength);
                mTablineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                // 当页面被选中时，改变tab标签的颜色，并记录当前页面的位置
                mChatTv.setTextColor(position == 0 ? getResources().getColor(R.color.vp_selected) : getResources().getColor(android.R.color.black));
                mFindTv.setTextColor(position == 1 ? getResources().getColor(R.color.vp_selected) : getResources().getColor(android.R.color.black));
                mContactTv.setTextColor(position == 2 ? getResources().getColor(R.color.vp_selected) : getResources().getColor(android.R.color.black));
                mCurIntex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogHelper.d(WeiChatVPActivity.class, "当前状态：" + state);
            }
        });
    }
}
