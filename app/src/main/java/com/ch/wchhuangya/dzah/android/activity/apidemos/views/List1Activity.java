package com.ch.wchhuangya.dzah.android.activity.apidemos.views;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.ch.wchhuangya.dzah.android.util.Constant;

/**
 * 列表的数据来源于字符数据
 * Dzah
 * Created by wchya on 2015-10-25 10:10.
 */
public class List1Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Constant.sCheeseStrings));
        getListView().setTextFilterEnabled(true);
    }
}
