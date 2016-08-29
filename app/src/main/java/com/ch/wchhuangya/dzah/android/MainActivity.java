package com.ch.wchhuangya.dzah.android;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ch.wchhuangya.dzah.android.components.XGPush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 主页
 * Created by wchya on 2015-08-17.
 */
public class MainActivity extends BaseActivity {
    /** 列表中显示的一级数据 */
    private List<Map<String, Object>> mDataList = new ArrayList<>();
    /** 列表中显示的非一级数据 */
    private Map<String, List<Map<String, Object>>> mDataMap = new HashMap<>();
    /** 键值：标题 */
    public static final String KEY_TITLE = "TITLE";
    /** 键值：Activity的class */
    public static final String KEY_ACTIVITY = "ACTIVITY";
    /** 键值：是否有子列表 */
    public static final String KEY_HAS_CHILD = "HAS_CHILD";
    /** 键值：子列表的TAG */
    public static final String KEY_TAG = "TAG";
    /** 键值：子列表自定义视图的TAG值 */
    public static final String TAG_CUSTOM_VIEW = "CUSTOM_VIEW";
    /** 记录访问历史的Stack */
    private Stack<List<Map<String, Object>>> mHistoryStack = new Stack<>();

    /** 显示数据的ListView */
    private ListView mListView;
    /** 列表使用的适配器 */
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        activity = this;
        // 查看信鸽是否注册过，已经注册则什么都不干，没有注册则注册信鸽
        //if(SharedPreferencesHelper.getString(activity, Constant.SP_NAME_COMPONENTS, Constant.CPN_XPUSH_TOKEN) == null)
        XGPush.registerPush(activity, true);

        init();
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.main_listview);

        // 初始化一级数据
        initDataList();
        // 初始化一级以下数据
        initDataMap();
        // 记录访问历史的根
        mHistoryStack.push(mDataList);

        // 初始化ListView
    }

    /** 初始化ListView */
    private void initListView() {
        mListView = (ListView) findViewById(R.id.main_listview);
        mAdapter = new SimpleAdapter(activity, mDataList, android.R.layout.simple_list_item_1, new String[]{KEY_TITLE}, new int[]{android.R.id.text1});
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String, Object> map = mDataList.get(i);

                if (((boolean)map.get(KEY_HAS_CHILD))) { // 如果有下级

                } else {

                }
            }
        });
    }

    /** 初始化一级数据 */
    private void initDataList() {

        initCustomViewDataList();
    }

    /** 初始化自定义视图数据 */
    private void initCustomViewDataList() {
        Map<String, Object> map = new HashMap<>();

        map.put(KEY_TITLE, "自定义View");
        map.put(KEY_HAS_CHILD, true);
        map.put(KEY_TAG, TAG_CUSTOM_VIEW);
        map.put(KEY_ACTIVITY, "");
        mDataList.add(map);

        map = new HashMap<>();
    }

    /** 初始化一级以下的数据 */
    private void initDataMap() {
        initCustomViewDataMap();
    }

    /** 初始化自定义View二级数据 */
    private void initCustomViewDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put(KEY_TITLE, "自定义View的测量");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, "");
        list.add(map);

        mDataMap.put(TAG_CUSTOM_VIEW, list);
    }
}
