package com.ch.wchhuangya.dzah.android.activity.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.LogHelper;

/**
 * 日程内容提供器
 * Created by wchya on 16/9/20.
 */

public class CalendarProviderActivity extends BaseActivity {
    private Button mSearchBtn;
    private Button mAddBtn;
    private ListView mListView;
    private TextView mEmptyTv;
    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_provider);

        activity = this;
        init();
    }

    private void init() {
        mSearchBtn = (Button) findViewById(R.id.calendar_provider_search_btn);
        mListView = (ListView) findViewById(R.id.calendar_provider_listview);
        mEmptyTv = (TextView) findViewById(R.id.calendar_provider_empty_tv);

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = null;
                ContentResolver cr = getContentResolver();
                Uri uri = CalendarContract.Calendars.CONTENT_URI;

                cursor = cr.query(uri, EVENT_PROJECTION, null, null, null);
                int i = 0;
                while (cursor.moveToNext()) {
                    i++;
                    long calID = 0;
                    String displayName = null;
                    String accountName = null;
                    String ownerName = null;

                    // Get the field values
                    calID = cursor.getLong(PROJECTION_ID_INDEX);
                    displayName = cursor.getString(PROJECTION_DISPLAY_NAME_INDEX);
                    accountName = cursor.getString(PROJECTION_ACCOUNT_NAME_INDEX);
                    ownerName = cursor.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

                    LogHelper.d(CalendarProviderActivity.class, "id: " + calID + ", displayName: " + displayName
                            + ", accountName: " + accountName + ", ownerName: " + ownerName);
                }
                if (i == 0)
                    LogHelper.d(CalendarProviderActivity.class, "没有日历数据!");
            }
        });
    }
}
