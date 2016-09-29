package com.ch.wchhuangya.dzah.android.activity.contentprovider;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

/**
 * 根据人员姓名查找通讯录联系人
 * Created by wchya on 16/9/27.
 */

public class ContactsSearchByNameActivity extends BaseActivity {
    private static final String TAG_SEARCH_BY_NAME = "TAG_SEARCH_BY_NAME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_search_by_name);

        ContactsSearchByNameFragment searchByNameFragment = new ContactsSearchByNameFragment();
        getFragmentManager().beginTransaction().add(R.id.contacts_search_by_name_ll, searchByNameFragment, TAG_SEARCH_BY_NAME).commit();
    }
}
