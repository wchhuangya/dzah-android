package com.ch.wchhuangya.dzah.android.activity.apidemos.views;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;

/**
 * SimpleAdapter显示联系人数据
 * Created by wchya on 2015-10-25.
 */
public class List2Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, CONTACT_PROJECTION, null, null, null);
        startManagingCursor(c);

        ListAdapter adapter = new SimpleCursorAdapter(this,
                //使用模板来显示文本
                android.R.layout.simple_list_item_1,
                // 给列表的适配器绑定cursor
                c,
                // 把人员数据库里的NAME列匹配到
                new String[] {ContactsContract.Contacts.DISPLAY_NAME},
                // 定义在模块中的ID为text1的视图上
                new int[] {android.R.id.text1}
        );
        setListAdapter(adapter);
    }

    private static final String[] CONTACT_PROJECTION = new String[]{
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
    };
}
