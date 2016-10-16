package com.ch.wchhuangya.dzah.android.activity.contentprovider;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 通话记录监听
 * Created by wchya on 16/10/14.
 */

public class CallsProviderActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.common_listview_search_et)
    EditText mCommonListviewSearchEt;
    @Bind(R.id.common_listview_search_tv)
    TextView mCommonListviewSearchTv;
    @Bind(R.id.common_listview_search_ll)
    LinearLayout mCommonListviewSearchLl;
    @Bind(android.R.id.list)
    ListView mList;
    @Bind(R.id.common_listview_tv)
    TextView mCommonListviewTv;

    /** 通话记录 URI */
    private static final String URI = "content://call_log/calls";
    /** 列表适配器 */
    private CustomAdapter mAdapter;
    /** 用于搜索的关键字 */
    private static String search = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calls_provider);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mAdapter = new CustomAdapter(activity, null, 0);
        mList.setAdapter(mAdapter);
        mList.setEmptyView(mCommonListviewTv);
        mCommonListviewSearchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = mCommonListviewSearchEt.getText().toString().trim();
                getLoaderManager().restartLoader(0, null, CallsProviderActivity.this);
                mList.smoothScrollToPosition(0);
            }
        });

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CustomLoader(activity);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    static class CustomLoader extends AsyncTaskLoader<Cursor> {
        private Context mContext;

        public CustomLoader(Context context) {
            super(context);
            mContext = context;
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = mContext.getContentResolver().query(Uri.parse(URI), null,
                    CallLog.Calls.NUMBER + " like ? or " + CallLog.Calls.CACHED_NAME + " like ?",
                    new String[]{"%" + search + "%", "%" + search + "%"}, CallLog.Calls.DEFAULT_SORT_ORDER);
            return cursor;
        }
    }

    class CustomAdapter extends CursorAdapter {

        public CustomAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = View.inflate(context, R.layout.calls_provider_item, null);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            Holder holder = (Holder) view.getTag();
            if (holder == null) {
                holder = new Holder();
                holder.mDateTv = (TextView) view.findViewById(R.id.calls_provider_item_date_tv);
                holder.mPhoneTv = (TextView) view.findViewById(R.id.calls_provider_item_phone_tv);
                holder.mTypeTv = (TextView) view.findViewById(R.id.calls_provider_item_type_tv);
                holder.mNameTv = (TextView) view.findViewById(R.id.calls_provider_item_name_tv);
            }

            long date = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
            String phone = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)),
                   name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
            int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE)),
                duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));

            holder.mDateTv.setText(TimeHelper.changeTimestampToTime(date, null));
            holder.mPhoneTv.setText(phone);
            holder.mTypeTv.setText(getCallType(type, duration));
            holder.mNameTv.setText(name);
        }

        class Holder {
            private TextView mDateTv;
            private TextView mPhoneTv;
            private TextView mTypeTv;
            private TextView mNameTv;
        }
    }

    /**
     * type = 1，来电；type = 2，去电；type = 3，未接；
     * type = 1 && duration = 0，骚扰
     * type = 2 && duration = 0，挂断
     */
    private String getCallType(int type, int duration) {
        switch (type) {
            case CallLog.Calls.INCOMING_TYPE:
                if (duration == 0)
                    return "未接";
                else
                    return "呼入" + TimeHelper.getFriendlyTime(duration);
            case CallLog.Calls.OUTGOING_TYPE:
                if (duration == 0)
                    return "呼出";
                else
                    return "呼出" + TimeHelper.getFriendlyTime(duration);
            case CallLog.Calls.MISSED_TYPE:
                return "未接";
        }
        return "未知";
    }
}
