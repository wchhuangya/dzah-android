package com.ch.wchhuangya.dzah.android.activity.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.adapter.ContactAdapter;
import com.ch.wchhuangya.dzah.android.model.Contact;

/**
 * Created by wchya on 2016-11-23 21:36
 */

public class ContactActivity extends FragmentActivity {

    private RecyclerView mRecyclerview;
    private LinearLayoutManager mLayoutManager;
    private ContactAdapter mAdapter;
    private int mStart = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_contact_list);

        mRecyclerview = (RecyclerView) findViewById(R.id.contact_list_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ContactAdapter();
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);

        fillData();

        findViewById(R.id.contact_list_add_btn).setOnClickListener(view -> {
            fillData();
        });
    }

    private void fillData() {
        mAdapter.setData(Contact.generateContacts(20), mStart, 20);
        mRecyclerview.scrollToPosition(mStart);
        mStart += 20;
    }
}
