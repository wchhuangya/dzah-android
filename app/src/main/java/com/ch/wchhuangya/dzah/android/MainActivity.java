package com.ch.wchhuangya.dzah.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ch.wchhuangya.dzah.android.activity.customview.ArcRatioActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.MeasureModelActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.TestCustomViewActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.TextViewFlickerActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.TextViewMultiBackgroundActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.TopBarActivity;
import com.ch.wchhuangya.dzah.android.activity.provider.SmsPVActivity;
import com.ch.wchhuangya.dzah.android.activity.rxandroid.RxAndroidActivity;
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
    /** 键值：子列表RxAndroid的TAG值 */
    public static final String TAG_RX_ANDROID = "RX_ANDROID";
    private Stack<List<Map<String, Object>>> mHistoryStack = new Stack<>();
    /** 标识是否退出的变量 */
    private boolean ifExit = false;

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
        initRxAndroidDataMap();
        // 记录访问历史的根
        mHistoryStack.push(mDataList);

        // 初始化ListView
        initListView();
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
                    mDataList = mDataMap.get(map.get(KEY_TAG));
                    mHistoryStack.push(mDataMap.get(map.get(KEY_TAG)));
                    mAdapter = new SimpleAdapter(activity, mDataList, android.R.layout.simple_list_item_1, new String[]{KEY_TITLE}, new int[]{android.R.id.text1});
                    mListView.setAdapter(mAdapter);
                } else { // 如果没有下级,直接打开页面
                    intent = new Intent(activity, (Class<?>) map.get(KEY_ACTIVITY));
                    startActivity(intent);
                }
            }
        });
    }

    /** 初始化一级数据 */
    private void initDataList() {

        Map<String, Object> map = new HashMap<>();

        // 初始化自定义视图数据
        map.put(KEY_TITLE, "自定义View");
        map.put(KEY_HAS_CHILD, true);
        map.put(KEY_TAG, TAG_CUSTOM_VIEW);
        map.put(KEY_ACTIVITY, "");
        mDataList.add(map);

        // 初始化短信数据
        map = new HashMap<>();
        map.put(KEY_TITLE, "短信");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, SmsPVActivity.class);
        mDataList.add(map);

        // 初始化RxAndroid数据
        map = new HashMap<>();
        map.put(KEY_TITLE, "RxAndroid");
        map.put(KEY_HAS_CHILD, true);
        map.put(KEY_TAG, TAG_RX_ANDROID);
        map.put(KEY_ACTIVITY, "");
        mDataList.add(map);
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
        map.put(KEY_ACTIVITY, MeasureModelActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "自定义 TextView —— 双框");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, TextViewMultiBackgroundActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "自定义 TextView —— 文本闪烁");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, TextViewFlickerActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "自定义 TextView —— 顶部工具栏");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, TopBarActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "自定义 View —— 圆弧比例图");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, ArcRatioActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "自定义 View —— 测试/复习");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, TestCustomViewActivity.class);
        list.add(map);

        mDataMap.put(TAG_CUSTOM_VIEW, list);
    }

    private void initRxAndroidDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put(KEY_TITLE, "简单示例");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, RxAndroidActivity.class);
        list.add(map);

        mDataMap.put(TAG_RX_ANDROID, list);
    }

    @Override
    public void onBackPressed() {
        if (mHistoryStack.size() > 1) {
            mHistoryStack.pop();
            mDataList = mHistoryStack.peek();
            mAdapter = new SimpleAdapter(activity, mDataList, android.R.layout.simple_list_item_1, new String[]{KEY_TITLE}, new int[]{android.R.id.text1});
            mListView.setAdapter(mAdapter);
        } else {
            if (!ifExit) {
                ifExit = true;
                showToast("请在两秒内再次按下后退键", 0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ifExit = false;
                    }
                }, 2000);
            } else
                super.onBackPressed();
        }
    }
}
