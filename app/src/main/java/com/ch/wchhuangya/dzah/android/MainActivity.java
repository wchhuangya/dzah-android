package com.ch.wchhuangya.dzah.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ch.wchhuangya.dzah.android.activity.contentprovider.CalendarProviderActivity;
import com.ch.wchhuangya.dzah.android.activity.contentprovider.CallsProviderActivity;
import com.ch.wchhuangya.dzah.android.activity.contentprovider.ContactsProviderActivity;
import com.ch.wchhuangya.dzah.android.activity.contentprovider.ContactsSearchByNameActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.ArcRatioActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.AudioBarChartActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.MeasureModelActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.TestCustomViewActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.TextViewFlickerActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.TextViewMultiBackgroundActivity;
import com.ch.wchhuangya.dzah.android.activity.customview.TopBarActivity;
import com.ch.wchhuangya.dzah.android.activity.databinding.CreateAndAssignActivity;
import com.ch.wchhuangya.dzah.android.activity.provider.SmsPVActivity;
import com.ch.wchhuangya.dzah.android.activity.retrofit.getipins.RetrofitTestActivity;
import com.ch.wchhuangya.dzah.android.activity.retrofit.github.GetContributorsActivity;
import com.ch.wchhuangya.dzah.android.activity.retrofit.zhihu.GetLatestActivity;
import com.ch.wchhuangya.dzah.android.activity.rxandroid.RxCreateActivity;
import com.ch.wchhuangya.dzah.android.activity.rxandroid.RxAndroidActivity;
import com.ch.wchhuangya.dzah.android.activity.rxandroid.ShowPhoneDBListActivity;
import com.ch.wchhuangya.dzah.android.activity.sms.SendIntentSendToSmsActivity;
import com.ch.wchhuangya.dzah.android.activity.sms.SendIntentViewSmsActivity;
import com.ch.wchhuangya.dzah.android.activity.sms.SendSmsWithBroadcastBySmsManagerActivity;
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
    /** 键值：子列表RxAndroid的TAG值 */
    public static final String TAG_RX_ANDROID = "RX_ANDROID";
    /** 键值：子列表Retrofit的Tag值 */
    public static final String TAG_RETROFIT = "RX_RETROFIT";
    /** 键值：子列表Databinding的Tag值 */
    public static final String TAG_DATABINDING = "DATABINDING";
    /** 键值：子列表 ContentProvider 的TAG值 */
    public static final String TAG_CONTENT_PROVIDER = "CONTENT_PROVIDER";
    /** 键值：子列表 ContentProvider——Contacts Provider 的TAG值 */
    public static final String TAG_CONTENT_PROVIDER_CONTACTS = "CONTENT_PROVIDER_CONTACTS";
    /** 键值：子列表 SMS 的TAG值 */
    public static final String TAG_SMS = "TAG_SMS";
    /** 记录访问历史的Stack */
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

        /*BmobUpdateAgent.initAppVersion(this);
        BmobUpdateAgent.setUpdateOnlyWifi(false);
        BmobUpdateAgent.update(this);*/
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
        initListView();
    }

    /** 初始化ListView */
    private void initListView() {
        mListView = (ListView) findViewById(R.id.main_listview);
        mAdapter = new SimpleAdapter(activity, mDataList, android.R.layout.simple_list_item_1, new String[]{KEY_TITLE}, new int[]{android.R.id.text1});
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Map<String, Object> map = mDataList.get(i);

            if (((boolean)map.get(KEY_HAS_CHILD))) { // 如果有下级
                mDataList = mDataMap.get(map.get(KEY_TAG));
                mHistoryStack.push(mDataMap.get(map.get(KEY_TAG)));
                mAdapter = new SimpleAdapter(activity, mDataList, android.R.layout.simple_list_item_1, new String[]{KEY_TITLE},
                        new int[]{android.R.id.text1});
                mListView.setAdapter(mAdapter);
            } else { // 如果没有下级,直接打开页面
                intent = new Intent(activity, (Class<?>) map.get(KEY_ACTIVITY));
                startActivity(intent);
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = mDataList.get(position);
                if (map.get(KEY_ACTIVITY) == RxAndroidActivity.class) {
                    intent = new Intent(activity, ShowPhoneDBListActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
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

        // 初始化Retrofit数据
        map = new HashMap<>();
        map.put(KEY_TITLE, "Retrofit");
        map.put(KEY_HAS_CHILD, true);
        map.put(KEY_TAG, TAG_RETROFIT);
        map.put(KEY_ACTIVITY, "");
        mDataList.add(map);

        // 初始化Databinding数据
        map = new HashMap<>();
        map.put(KEY_TITLE, "Databinding");
        map.put(KEY_HAS_CHILD, true);
        map.put(KEY_TAG, TAG_DATABINDING);
        map.put(KEY_ACTIVITY, "");
        mDataList.add(map);

        // 初始化内容提供器数据
        map = new HashMap<>();
        map.put(KEY_TITLE, "内容提供器");
        map.put(KEY_HAS_CHILD, true);
        map.put(KEY_TAG, TAG_CONTENT_PROVIDER);
        map.put(KEY_ACTIVITY, "");
        mDataList.add(map);

        // 初始化内容提供器数据
        map = new HashMap<>();
        map.put(KEY_TITLE, "短信");
        map.put(KEY_HAS_CHILD, true);
        map.put(KEY_TAG, TAG_SMS);
        map.put(KEY_ACTIVITY, "");
        mDataList.add(map);
    }

    /** 初始化一级以下的数据 */
    private void initDataMap() {
        initCustomViewDataMap();
        initRxAndroidDataMap();
        initRetrofitDataMap();
        initDatabindingtDataMap();
        initContentProviderDataMap();
        initContentProviderContactDataMap();
        initSMSDataMap();
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
        map.put(KEY_TITLE, "自定义 View —— 音频条状图");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, AudioBarChartActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "自定义 View —— 测试/复习");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, TestCustomViewActivity.class);
        list.add(map);

        mDataMap.put(TAG_CUSTOM_VIEW, list);
    }

    /** 初始化 RxAndroid 二级数据 */
    private void initRxAndroidDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put(KEY_TITLE, "简单示例");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, RxAndroidActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "创建操作");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, RxCreateActivity.class);
        list.add(map);

        mDataMap.put(TAG_RX_ANDROID, list);
    }

    /** 初始化 Retrofit 二级数据 */
    private void initRetrofitDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put(KEY_TITLE, "根据 IP 获取地理相关信息（繁）");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, RetrofitTestActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "获取 GitHub 某个仓库的贡献者列表");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, GetContributorsActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "获取知乎最新消息列表");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, GetLatestActivity.class);
        list.add(map);

        mDataMap.put(TAG_RETROFIT, list);
    }

    /** 初始化 Retrofit 二级数据 */
    private void initDatabindingtDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put(KEY_TITLE, "创建时绑定");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, CreateAndAssignActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "获取 GitHub 某个仓库的贡献者列表");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, GetContributorsActivity.class);
        list.add(map);

        mDataMap.put(TAG_DATABINDING, list);
    }

    /** 初始化 ContentProvider 二级数据 */
    private void initContentProviderDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put(KEY_TITLE, "日历内容提供器");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, CalendarProviderActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "联系人内容提供器");
        map.put(KEY_HAS_CHILD, true);
        map.put(KEY_TAG, TAG_CONTENT_PROVIDER_CONTACTS);
        map.put(KEY_ACTIVITY, "");
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "通话记录内容提供器");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, CallsProviderActivity.class);
        list.add(map);

        mDataMap.put(TAG_CONTENT_PROVIDER, list);
    }

    /** 初始化 Contacts Provider 三级数据 */
    private void initContentProviderContactDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put(KEY_TITLE, "初试");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, ContactsProviderActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "查询联系人");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, ContactsSearchByNameActivity.class);
        list.add(map);

        mDataMap.put(TAG_CONTENT_PROVIDER_CONTACTS, list);
    }

    /** 初始化 SMS 二级数据 */
    private void initSMSDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put(KEY_TITLE, "使用 ACTION_SENDTO 发送短信");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, SendIntentSendToSmsActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "使用 ACTION_VIEW 发送短信");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, SendIntentViewSmsActivity.class);
        list.add(map);

        map = new HashMap<>();
        map.put(KEY_TITLE, "使用 SmsManager 发送短信发广播");
        map.put(KEY_HAS_CHILD, false);
        map.put(KEY_TAG, "");
        map.put(KEY_ACTIVITY, SendSmsWithBroadcastBySmsManagerActivity.class);
        list.add(map);

        mDataMap.put(TAG_SMS, list);
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
                showToast("请再次按键后退出", 0);
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
