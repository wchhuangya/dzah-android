package com.ch.wchhuangya.dzah.android.activity.provider;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.ContentValues;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.enums.SmsType;
import com.ch.wchhuangya.dzah.android.model.Sms;
import com.ch.wchhuangya.dzah.android.util.LogHelper;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 短信内容提供器操作
 * Created by wchya on 2016-01-04 20:40.
 */
public class SmsPVActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.show_btn)
    Button mShowBtn;
    @Bind(R.id.search_btn)
    Button mSearchBtn;
    @Bind(R.id.search_et)
    EditText mSearchEt;
    @Bind(R.id.listview)
    ListView mListview;
    @Bind(R.id.sms_count_tv)
    TextView mSmsCountTv;
    @Bind(R.id.insert_btn)
    Button mInsertBtn;

    private Cursor mCursor;
    private String[] projection = new String[]{
            Sms.FIELD_ID, Sms.FIELD_BODY, Sms.FIELD_ADDRESS, Sms.FIELD_TYPE, Sms.FIELD_DATE, Sms.FIELD_DATE_SENT, Sms.FIELD_LOCKED, Sms.FIELD_SEEN, Sms.FIELD_READ};
    private String selection = " address like ? or address is null";
    private String search = "";
    private String[] selectionArgs = { "%" + search + "%"};
    final ArrayList<String> list = new ArrayList<>();
    private String uri;

    private CustomAdapter mCustomAdapter;

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CustomLoader(activity, search);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCustomAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCustomAdapter.swapCursor(null);
    }

    public static class CustomLoader extends AsyncTaskLoader<Cursor> {
        private Context mContext;
        private String search;

        public CustomLoader(Context context, String search) {
            super(context);
            mContext = context;
            this.search = search;
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = mContext.getContentResolver().query(Uri.parse("content://sms/"), new String[]{
                    Sms.FIELD_ID, Sms.FIELD_BODY, Sms.FIELD_ADDRESS, Sms.FIELD_TYPE, Sms.FIELD_DATE, Sms.FIELD_DATE_SENT, Sms.FIELD_SEEN, Sms.FIELD_READ},
                    " address like ? or address is null", new String[]{"%" + search + "%"}, " date DESC");
            return cursor;
        }
    }

    class CustomAdapter extends CursorAdapter {

        public CustomAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = View.inflate(context, R.layout.sms_pv_list_item, null);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            Holder holder = (Holder) view.getTag();
            if (holder == null) {
                holder = new Holder();
                holder.mIdTv = (TextView) view.findViewById(R.id.sms_pv_id_tv);
                holder.mTypeTv = (TextView) view.findViewById(R.id.sms_pv_type_tv);
                holder.mSeeTv = (TextView) view.findViewById(R.id.sms_pv_see_tv);
                holder.mReadTv = (TextView) view.findViewById(R.id.sms_pv_read_tv);
                holder.mNumTv = (TextView) view.findViewById(R.id.sms_pv_num_tv);
                holder.mContentTv = (TextView) view.findViewById(R.id.sms_pv_content_tv);
                holder.mReceiveTimeTv = (TextView) view.findViewById(R.id.sms_pv_receive_time_tv);
                holder.mSendTimeTv = (TextView) view.findViewById(R.id.sms_pv_send_time_tv);
                view.setTag(holder);
            }

            holder.mIdTv.setText("序号: " + cursor.getString(cursor.getColumnIndex(Telephony.Sms._ID)));
            holder.mTypeTv.setText(String.format("【%s】", SmsType.getValue(cursor.getInt(cursor.getColumnIndex(Sms.FIELD_TYPE)))));
            String see = cursor.getInt(cursor.getColumnIndex(Sms.FIELD_SEEN)) == 0 ? "未看" : "已看";
            holder.mSeeTv.setText(see);
            String read = cursor.getInt(cursor.getColumnIndex(Sms.FIELD_READ)) == 0 ? "未读" : "已读";
            holder.mReadTv.setText(read);
            String address = cursor.getString(cursor.getColumnIndex(Sms.FIELD_ADDRESS)) == null ? "无"
                    : cursor.getString(cursor.getColumnIndex(Sms.FIELD_ADDRESS));
            holder.mNumTv.setText("电话号码: " + address);
            holder.mContentTv.setText(cursor.getString(cursor.getColumnIndex(Sms.FIELD_BODY)));
            String receiveTime = TimeHelper.changeTimestampToTime(cursor.getLong(cursor.getColumnIndex(Sms.FIELD_DATE)), null);
            holder.mReceiveTimeTv.setText("接收时间: " + receiveTime);
            String sendTime = TimeHelper.changeTimestampToTime(cursor.getLong(cursor.getColumnIndex(Sms.FIELD_DATE_SENT)), null);
            holder.mSendTimeTv.setText("发送时间: " + sendTime);
        }

        class Holder {
            public TextView mIdTv;
            public TextView mTypeTv;
            public TextView mSeeTv;
            public TextView mReadTv;
            public TextView mNumTv;
            public TextView mContentTv;
            public TextView mReceiveTimeTv;
            public TextView mSendTimeTv;
        }
    }

    /**
     * 短信类型枚举
     */
    public enum SMSType {
        MESSAGE_TYPE_ALL(0),
        MESSAGE_TYPE_INBOX(1),
        MESSAGE_TYPE_SENT(2),
        MESSAGE_TYPE_DRAFT(3),
        MESSAGE_TYPE_OUTBOX(4),
        MESSAGE_TYPE_FAILED(5),
        MESSAGE_TYPE_QUEUED(6);

        private int type;

        private SMSType(int type) {
            this.type = type;
        }

        public int getValue() {
            return type;
        }

        @Override
        public String toString() {
            int type = 0;
            switch (this) {
                case MESSAGE_TYPE_ALL:
                    type = 0;
                    break;
                case MESSAGE_TYPE_INBOX:
                    type = 1;
                    break;
                case MESSAGE_TYPE_SENT:
                    type = 2;
                    break;
                case MESSAGE_TYPE_DRAFT:
                    type = 3;
                    break;
                case MESSAGE_TYPE_OUTBOX:
                    type = 4;
                    break;
                case MESSAGE_TYPE_FAILED:
                    type = 5;
                    break;
                case MESSAGE_TYPE_QUEUED:
                    type = 6;
                    break;
            }
            return type + "";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_pv);
        ButterKnife.bind(this);

        activity = this;
        init();
    }

    private void init() {
        getLoaderManager().initLoader(0, null, this);
        mCustomAdapter = new CustomAdapter(this, null, false);
        mListview.setAdapter(mCustomAdapter);
    }

    @OnClick(R.id.search_btn)
    public void searchByPhone(View view) {
        search = mSearchEt.getText().toString().trim();
        getLoaderManager().restartLoader(0, null, SmsPVActivity.this);
    }

    @OnClick(R.id.show_btn)
    public void show(View view) {
        new QueryAllAsync().execute("");
    }

    @OnClick(R.id.insert_btn)
    public void insert(View view) {
        insertMsg();
    }

    private class QueryAllAsync extends AsyncTask<String, Integer, Boolean> {
        String phone = "";

        @Override
        protected void onPreExecute() {
            showProgressDialog("", "正在查询短信,请稍候...", false);
            if (!mSearchEt.getText().toString().trim().equals(""))
                phone = mSearchEt.getText().toString().trim();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                uri = Sms.URI_ALL;
                if (!TextUtils.isEmpty(phone)) {
                    uri = Sms.URI_SEND;
                    selection = "address = ? and type = ?";
                    selectionArgs = new String[]{phone, Sms.TYPE_SENT + ""};
                }

                mCursor = getContentResolver().query(Uri.parse(uri), projection, selection, selectionArgs, "date DESC");
                return true;
            } catch (Exception e) {
                LogHelper.e(QueryAllAsync.class, e.getMessage());
                msg = "读取短信失败：" + e.getMessage();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            try {
                if (aBoolean) {
                    if (aBoolean) {
                        if (mCursor != null) {
                            list.clear();
                            while (mCursor.moveToNext()) {
                                int type = mCursor.getInt(mCursor.getColumnIndex("type"));

                                // "_id", "body", "address", "type", "date", "date_sent", "locked"
                                String date = TimeHelper.changeTimestampToTime(mCursor.getLong(mCursor.getColumnIndex(Sms.FIELD_DATE)), null);
                                String dateSent = TimeHelper.changeTimestampToTime(mCursor.getLong(mCursor.getColumnIndex(Sms.FIELD_DATE_SENT)), null);
                                list.add("【" + Sms.typeToString(mCursor.getInt(mCursor.getColumnIndex(Sms.FIELD_TYPE))) + "】        "
                                        + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_ADDRESS)) + "："
                                        + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_BODY))
                                        + "        【是否已读：" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_READ)) + "】"
                                        + "        【是否看到：" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_SEEN)) + "】"
                                        + "        【locked：" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_LOCKED)) + "】"
                                        + "        【接收时间：" + date + "】"
                                        + "        【发送时间：" + dateSent + "】" + "|||" + mCursor.getString(mCursor.getColumnIndex(Sms.FIELD_ID)));
                            }
                            if (mCursor.getCount() == 0)
                                showToast("没有任何数据!");
                            else {
                                mSmsCountTv.setVisibility(View.VISIBLE);
                                mSmsCountTv.setText("总共  " + mCursor.getCount() + "  条短信");
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, list);
                                mListview.setAdapter(adapter);
                                mListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                    @Override
                                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                        String s = list.get(position);
                                        view.setTag(s.substring(s.indexOf("|||") + 3, s.length()));
                                        registerForContextMenu(view);
                                        return false;
                                    }
                                });
                            }
                        } else
                            showErrorMsg("读取短信失败!");
                    } else
                        showErrorMsg("读取短信失败!");
                } else
                    showErrorMsg("读取短信失败!");
            } catch (Exception e) {
                LogHelper.e(QueryAllAsync.class, e.getMessage());
                showToast("读取短信失败：" + e.getMessage());
            } finally {
                cancelProgressDialog();
                if (mCursor != null)
                    mCursor.close();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 0, 1, "删除").setActionView(v);
        menu.add(0, 1, 2, "锁定").setActionView(v);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String id = item.getActionView().getTag().toString();
        switch (item.getItemId()) {
            case 0:
                deleteMsg(id);
                break;
            case 1:
                lockMsg(id);
                break;
        }
        showToast(id);
        return super.onContextItemSelected(item);
    }

    private void deleteMsg(String id) {
        // 删除短信之前判断应用是否有删除短信的权限，如果没有，复用反射的方法获取权限
        // 经过在5.1机器上测试，不需要这个也可以删除
        /*if (!SmsWriteOpUtil.isWriteEnabled(getApplicationContext())) {
            SmsWriteOpUtil.setWriteEnabled(getApplicationContext(), true);
        }*/
        int i = getContentResolver().delete(Uri.parse(uri), "_id = ?", new String[]{id});
        if (i > 0)
            new QueryAllAsync().execute("");
        else
            showToast("删除失败!");
    }

    private void lockMsg(String id) {
        ContentValues values = new ContentValues();
        values.put("locked", 1);
        int i = getContentResolver().update(Uri.parse(uri), values, "_id = ?", new String[]{id});
        if (i > 0)
            new QueryAllAsync().execute("");
        else
            showToast("锁定失败!");
    }

    private void insertMsg() {
        ContentValues values = new ContentValues();
        values.put(Sms.FIELD_ADDRESS, "12345678901");
        values.put(Sms.FIELD_BODY, "功能测试");
        values.put(Sms.FIELD_DATE, TimeHelper.getCurrentTimestamp());
        values.put(Sms.FIELD_DATE_SENT, TimeHelper.getCurrentTimestamp());
        values.put(Sms.FIELD_TYPE, Sms.TYPE_INBOX);
        values.put(Sms.FIELD_SEEN, "1");
        values.put(Sms.FIELD_READ, "1");
        Uri u = getContentResolver().insert(Uri.parse(Sms.URI_ALL), values);
        if (null != u)
            new QueryAllAsync().execute("");
        else
            showToast("插入失败!");
    }
}
