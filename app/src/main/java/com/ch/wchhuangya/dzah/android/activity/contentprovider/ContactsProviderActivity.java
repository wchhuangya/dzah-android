package com.ch.wchhuangya.dzah.android.activity.contentprovider;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;


/**
 * 联系人提供器
 * Created by wchya on 16/9/21.
 */

public class ContactsProviderActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private ContentResolver mResolver;
    private TextView mResTv;
    private SimpleCursorAdapter mCursorAdapter;
    private static final int LOADER_ID = 0;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_provider);

        mResolver = getContentResolver();
        getLoaderManager().initLoader(LOADER_ID, null, this);

        mResTv = (TextView) findViewById(R.id.contacts_provider_res_tv);
        mResTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mListView = (ListView) findViewById(R.id.contacts_provider_listview);
        mCursorAdapter= new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null,
                new String[]{CallLog.Calls.NUMBER, CallLog.Calls.DATE}, new int[]{android.R.id.text1, android.R.id.text2 });
        mListView.setAdapter(mCursorAdapter);
        findViewById(R.id.contacts_provider_deleted_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rawId = "";
                String[] projection = {ContactsContract.RawContacts._ID, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY, ContactsContract.RawContacts.TIMES_CONTACTED};
                Cursor cursor = mResolver.query(ContactsContract.RawContacts.CONTENT_URI, projection,
                        ContactsContract.RawContacts.DELETED + "=?", new String[]{"1"}, null);
                mResTv.setText("");
                if (cursor.getCount() == 0) {
                    mResTv.setText("没有被删除的联系人!");
                } else {
                    while (cursor.moveToNext()) {
                        String displayName = "";
                        String timesContacted = "";
                        String id = "";
                        id = cursor.getInt(cursor.getColumnIndex(ContactsContract.RawContacts._ID)) + "";
                        displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY));
                        timesContacted = cursor.getInt(cursor.getColumnIndex(ContactsContract.RawContacts.TIMES_CONTACTED)) + "";
                        mResTv.append("id: " + id + ", 姓名: " + displayName + ", 联系次数: " + timesContacted + "\n");
                    }
                }
            }
        });
        findViewById(R.id.contacts_provider_double_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResTv.setText("");
                boolean hasAccount = false;
                // 先获取本机已有的账号
                String[] accountProjection = {ContactsContract.Groups._ID, ContactsContract.Groups.ACCOUNT_NAME,
                        ContactsContract.Groups.ACCOUNT_TYPE, ContactsContract.Groups.DATA_SET};
                Cursor accountCursor = mResolver.query(ContactsContract.Groups.CONTENT_URI, accountProjection, null, null, null);
                if (accountCursor == null || accountCursor.getCount() == 0) {
                    mResTv.setText("暂无账户信息!");
                } else {
                    while (accountCursor.moveToNext()) {
                        mResTv.append("id: " + accountCursor.getInt(accountCursor.getColumnIndex(ContactsContract.Groups._ID)) +
                                    ", 账户名称: " + accountCursor.getString(accountCursor.getColumnIndex(ContactsContract.Groups.ACCOUNT_NAME)) +
                                    ", 账户类型: " + accountCursor.getString(accountCursor.getColumnIndex(ContactsContract.Groups.ACCOUNT_TYPE)) +
                                    ", 数据集合: " + accountCursor.getString(accountCursor.getColumnIndex(ContactsContract.Groups.DATA_SET)) + "\n");
                    }
                    hasAccount = true;
                }
                // 再获取原始联系人数据
                String[] rawContactProjection = {ContactsContract.RawContacts._ID, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY};
                Cursor rawContactCursor = mResolver.query(ContactsContract.RawContacts.CONTENT_URI, rawContactProjection,
                        null, null, null);

                if (rawContactCursor != null && rawContactCursor.getCount() == 0) {
                    mResTv.append("没有原始联系人!\n");
                } else {
                    while (rawContactCursor.moveToNext()) {
                        mResTv.append("id: " + rawContactCursor.getString(rawContactCursor.getColumnIndex(ContactsContract.RawContacts._ID))
                                    + "姓名: " + rawContactCursor.getString(rawContactCursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY))
                                    + "\n");
                    }
                }
            }
        });
        findViewById(R.id.contacts_provider_del_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResolver.delete(ContactsContract.RawContacts.CONTENT_URI, null, null);
                mResolver.delete(ContactsContract.Data.CONTENT_URI, null, null);
                showToast("删除成功!");
            }
        });
        findViewById(R.id.contacts_provider_calls_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mResolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);
                mResTv.setText("");
                if (cursor != null && cursor.getCount() == 0) {
                    mResTv.setText("没有任何通话记录!");
                } else {
                    while (cursor.moveToNext()) {
                        mResTv.append("姓名: " + cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME))
                                    + "电话: " + cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER))
                                    + "类型: " + cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NUMBER_TYPE))
                                    + "data: " + cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE))
                                    + "type: " + cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE)) + "\n");
                    }
                }
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(ContactsProviderActivity.this, CallLog.Calls.CONTENT_URI,
                new String[]{CallLog.Calls._ID, CallLog.Calls.NUMBER, CallLog.Calls.TYPE, CallLog.Calls.DATE}, CallLog.Calls.TYPE + " not in ('1','2','3')", null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.changeCursor(null);
    }
}
