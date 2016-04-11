package com.ch.wchhuangya.dzah.android.activity.apidemos.views;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * 数据来源于cursor，每个item显示两行元素：电话号码、电话号码的类型（如
 * Created by wchya on 2015-10-25 18:16.
 */
public class List3Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取所有电话号码的cursor
        Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONE_PROJECTION, null, null, null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                c, new String[] {ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.NUMBER},
                new int[] {android.R.id.text1, android.R.id.text2});

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                // 如果当前列不是TYPE，把绑定交由adapter去处理
                if (columnIndex != COLUMN_TYPE) {
                    return false;
                }
                int type = cursor.getInt(COLUMN_TYPE);
                String label = null;
                // 如果是自定义类型，那么获取自定义的label
                if (type == ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM) {
                    label = cursor.getString(COLUMN_LABEL);
                }
                // 获取可读的字符串
                String text = (String) ContactsContract.CommonDataKinds.Phone.getTypeLabel(getResources(), type, label);
                // 设置文本
                ((TextView) view).setText(text);
                return true;
            }
        });

        setListAdapter(adapter);
    }

    private static final String[] PHONE_PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.TYPE,
            ContactsContract.CommonDataKinds.Phone.LABEL,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    private static final int COLUMN_TYPE = 1;;
    private static final int COLUMN_LABEL = 2;
}
