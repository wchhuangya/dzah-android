package com.ch.wchhuangya.dzah.android.activity.rxandroid;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.db.DBEnum;
import com.ch.wchhuangya.dzah.android.db.PhoneDB;
import com.ch.wchhuangya.dzah.android.util.LogHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 显示电话数据库记录的列表
 * Created by wchya on 16/10/2.
 */

public class ShowPhoneDBListActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(android.R.id.list)
    ListView mList;
    @Bind(R.id.common_listview_tv)
    TextView mCommonListviewTv;
    /**
     * 适配器
     */
    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_listview);
        ButterKnife.bind(this);

        getLoaderManager().initLoader(0, null, this);
        mAdapter = new SimpleCursorAdapter(activity, R.layout.phone_db_list_item, null,
                new String[]{PhoneDB.CallsRecord.PHONE_NUMBER, PhoneDB.CallsRecord.DATE_TIME, PhoneDB.CallsRecord.TYPE},
                new int[]{R.id.phone_db_list_item_number_tv, R.id.phone_db_list_item_date_tv, R.id.phone_db_list_item_type_tv}, 0);
        mList.setAdapter(mAdapter);
        mList.setEmptyView(mCommonListviewTv);
        mCommonListviewTv.setText("暂无数据");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CustomeLoader(activity);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    public static class CustomeLoader extends AsyncTaskLoader<Cursor> {
        private Context mContext;

        public CustomeLoader(Context context) {
            super(context);
            mContext = context;
        }

        @Override
        protected void onStartLoading() {
            LogHelper.i(ShowPhoneDBListActivity.class, "onStartLoading...");
            forceLoad();
        }

        @Override
        public Cursor loadInBackground() {
            LogHelper.i(ShowPhoneDBListActivity.class, "loadInBackground...");
            Cursor cursor = PhoneDB.getInstance(mContext).findAll(PhoneDB.CallsRecord.DATE_TIME, DBEnum.getOrder(DBEnum.DB_SORT_DESC));
            return cursor;
        }
    }
}
