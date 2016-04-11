package com.ch.wchhuangya.dzah.android.activity.apidemos.views;

import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

/**
 * Created by wchya on 2015-10-23.
 */
public class TabHost2Activity extends BaseActivity implements TabHost.TabContentFactory {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_host2);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab1", getResources().getDrawable(R.drawable.star_big_on)).setContent(this));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("tab2", getResources().getDrawable(R.drawable.star_big_on)).setContent(this));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("tab3", getResources().getDrawable(R.drawable.star_big_on)).setContent(this));
    }

    @Override
    public View createTabContent(String tag) {
        final TextView tv = new TextView(this);
        tv.setText("当前是" + tag + "标签下的内容");
        return tv;
    }
}
