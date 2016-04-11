package com.ch.wchhuangya.dzah.android.activity.apidemos.views;

import android.os.Bundle;
import android.widget.TabHost;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

/**
 * 拥有静态Label、xml中的content的TabHost
 * Created by wchya on 2015-10-13.
 */
public class TabHost1Activity extends BaseActivity {
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_host1);

        activity = this;

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("标签一").setContent(R.id.tab_host1_tv1));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("标签二").setContent(R.id.tab_host1_tv2));
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("标签三").setContent(R.id.tab_host1_tv3));
    }
}
