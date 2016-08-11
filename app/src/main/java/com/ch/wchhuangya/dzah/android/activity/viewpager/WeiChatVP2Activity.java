package com.ch.wchhuangya.dzah.android.activity.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.LogHelper;

import java.util.ArrayList;
import java.util.List;

public class WeiChatVP2Activity extends BaseActivity {

    private TextView mChatTv;
    private TextView mFindTv;
    private TextView mContactTv;
    private ImageView mTablineIv;
    private ViewPager mVpVp;

    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mList = new ArrayList<>();
    private int mTablineLength;
    private int mCurIntex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wei_chat_vp);

        init();
    }

    private void init() {
        // 初始化控件
        mChatTv = (TextView) findViewById(R.id.chat_tv);
        mFindTv = (TextView) findViewById(R.id.find_tv);
        mContactTv = (TextView) findViewById(R.id.contact_tv);
        mTablineIv = (ImageView) findViewById(R.id.tabline_iv);
        mVpVp = (ViewPager) findViewById(R.id.vp_vp);

        mChatTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurIntex != 0)
                    mVpVp.setCurrentItem(0, true);
            }
        });
        mFindTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurIntex != 1)
                    mVpVp.setCurrentItem(1, true);
            }
        });
        mContactTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurIntex != 2)
                    mVpVp.setCurrentItem(2, true);
            }
        });

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
        mVpVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogHelper.d(WeiChatVP2Activity.class, position + "    " + positionOffset + "    " + positionOffsetPixels + "    " + mCurIntex);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTablineIv.getLayoutParams();
                lp.leftMargin = (int) ((position + positionOffset) * mTablineLength);
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

            }
        });
    }
}
