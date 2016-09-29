package com.ch.wchhuangya.dzah.android.activity.contentprovider;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.R;

/**
 * 根据姓名查找联系人
 * Created by wchya on 16/9/27.
 */

public class ContactsSearchByNameFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {
    private ListView mListView;
    /** 没有搜索结果时显示信息的控件 */
    private TextView mTv;
    /** 搜索的文本 */
    private TextView mSearchTv;
    /** 搜索的输入框 */
    private EditText mSearchEt;
    /** ListView 使用的适配器 */
    private SimpleCursorAdapter mAdapter;
    /** 定义要显示的列名 */
    public static final String[] FROM_COLUMN = {Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
                                                    : ContactsContract.Contacts.DISPLAY_NAME};
    /** 用到 UI 控件的 ID */
    public static final int[] TO_ID = {android.R.id.text1};
    /** 定义要查询的列名 */
    private static final String[] PROJECTION = {ContactsContract.Contacts._ID, ContactsContract.Contacts.LOOKUP_KEY,
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                            ContactsContract.Contacts.DISPLAY_NAME};
    /** 定义结果游标中联系人 ID 的列索引 */
    private static final int CONTACT_ID_INDEX = 0;
    /** 定义结果游标中联系人 LOOKUP 的列索引 */
    private static final int LOOKUP_KEY_INDEX = 1;
    /** 查询条件 */
    private static final String SELECTION = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
                                    ? ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ? " : ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
    /** 保存查询条件的变量 */
    private String mSearch = "";
    /** 查询条件中的参数列表 */
    private String[] selectionArgs = {mSearch};

    /** 用户选择联系人的 ID */
    private long mContactId;
    /** 用户选择联系人的 LOOKUP KEY */
    private String mContactKey;
    /** 查询联系人详情的 Uri */
    private Uri mContactUri;
    /** 自定义的 Loader ID */
    private static final int LOADER_ID = 0;

    // 空的构造器是 Android 系统要求的
    public ContactsSearchByNameFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_search_by_name_fm, container, false);

        mListView = (ListView) view.findViewById(R.id.fm_search_by_name_listview);
        mTv = (TextView) view.findViewById(R.id.fm_search_by_name_tv);
        mSearchEt = (EditText) view.findViewById(R.id.fm_search_by_name_search_et);
        mSearchTv = (TextView) view.findViewById(R.id.fm_search_by_name_search_tv);
        mSearchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearch = mSearchEt.getText().toString();
                getLoaderManager().restartLoader(LOADER_ID, null, ContactsSearchByNameFragment.this);
            }
        });
        mListView.setEmptyView(mTv);
        mAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, null, FROM_COLUMN, TO_ID, 0);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        selectionArgs[0] = "%" + mSearch + "%";
        return new CursorLoader(getActivity(), ContactsContract.Contacts.CONTENT_URI ,PROJECTION, SELECTION, selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = ((SimpleCursorAdapter)parent.getAdapter()).getCursor();
        cursor.moveToPosition(position);
        mContactId = cursor.getLong(CONTACT_ID_INDEX);
        mContactKey = cursor.getString(LOOKUP_KEY_INDEX);
        mContactUri = ContactsContract.Contacts.getLookupUri(mContactId, mContactKey);

        // 用户点击某个联系人时,使用上面的查询 Uri,获取联系人的详细信息
    }
}
