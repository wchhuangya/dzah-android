package com.ch.wchhuangya.dzah.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ch.wchhuangya.dzah.android.activity.animation.RadiusChangedCircleActivity;
import com.ch.wchhuangya.dzah.android.activity.animation.SlidePicturesActivity;
import com.ch.wchhuangya.dzah.android.activity.canvas.BasicGraphicsActivity;
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
import com.ch.wchhuangya.dzah.android.activity.mvp.login.LoginActivity;
import com.ch.wchhuangya.dzah.android.activity.provider.SmsPVActivity;
import com.ch.wchhuangya.dzah.android.activity.recyclerview.ContactActivity;
import com.ch.wchhuangya.dzah.android.activity.recyclerview.HongYangTutorialActivity;
import com.ch.wchhuangya.dzah.android.activity.recyclerview.RefreshActivity;
import com.ch.wchhuangya.dzah.android.activity.recyclerview.TaoBaoGridActivity;
import com.ch.wchhuangya.dzah.android.activity.recyclerview.XampRVActivity;
import com.ch.wchhuangya.dzah.android.activity.retrofit.getipins.RetrofitTestActivity;
import com.ch.wchhuangya.dzah.android.activity.retrofit.github.GetContributorsActivity;
import com.ch.wchhuangya.dzah.android.activity.retrofit.zhihu.GetLatestActivity;
import com.ch.wchhuangya.dzah.android.activity.rxandroid.RxAndroidActivity;
import com.ch.wchhuangya.dzah.android.activity.rxandroid.RxCreateActivity;
import com.ch.wchhuangya.dzah.android.activity.rxandroid.ShowPhoneDBListActivity;
import com.ch.wchhuangya.dzah.android.activity.service.BindServiceBinderActivity;
import com.ch.wchhuangya.dzah.android.activity.service.StartServiceActivity;
import com.ch.wchhuangya.dzah.android.activity.sms.SendIntentSendToSmsActivity;
import com.ch.wchhuangya.dzah.android.activity.sms.SendIntentViewSmsActivity;
import com.ch.wchhuangya.dzah.android.activity.sms.SendSmsWithBroadcastBySmsManagerActivity;
import com.ch.wchhuangya.dzah.android.activity.viewpager.TuiCoolArticleActivity;
import com.ch.wchhuangya.dzah.android.activity.viewpager.WeiChatVPActivity;
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
    /** 键值：子列表四大组件的TAG值 */
    public static final String TAG_FOUR_COMPONENTS = "FOUR_COMPONENTS";
    /** 键值：子列表 Activity 的TAG值 */
    public static final String TAG_ACTIVITY = "ACTIVITY";
    /** 键值：子列表服务的TAG值 */
    public static final String TAG_SERVICE = "SERVICE";
    /** 键值：子列表广播的TAG值 */
    public static final String TAG_BROADCAST = "BORADCAST";
    /** 键值：子列表自定义视图的TAG值 */
    public static final String TAG_CUSTOM_VIEW = "CUSTOM_VIEW";
    /** 键值：子列表RxAndroid的TAG值 */
    public static final String TAG_RX_ANDROID = "RX_ANDROID";
    /** 键值：子列表Retrofit的Tag值 */
    public static final String TAG_RETROFIT = "RX_RETROFIT";
    /** 键值：子列表Databinding的Tag值 */
    public static final String TAG_DATABINDING = "DATABINDING";
    /** 键值：子列表RecyclerView的Tag值 */
    public static final String TAG_RECYCLERVIEW = "RECYCLERVIEW";
    /** 键值：子列表ViewPager的Tag值 */
    public static final String TAG_VIEWPAGER = "VIEWPAGER";
    /** 键值：子列表Animation的Tag值 */
    public static final String TAG_ANIMATION = "ANIMATION";
    /** 键值：子列表 ContentProvider 的TAG值 */
    public static final String TAG_CONTENT_PROVIDER = "CONTENT_PROVIDER";
    /** 键值：子列表 ContentProvider——Contacts Provider 的TAG值 */
    public static final String TAG_CONTENT_PROVIDER_CONTACTS = "CONTENT_PROVIDER_CONTACTS";
    /** 键值：子列表 SMS 的TAG值 */
    public static final String TAG_SMS = "TAG_SMS";
    /** 键值：子列表 代码结构 的TAG值 */
    public static final String TAG_ARCHITECTURE = "TAG_ARCHITECTURE";
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
                if (mDataMap.get(map.get(KEY_TAG)) != null) {
                    mDataList = mDataMap.get(map.get(KEY_TAG));
                    mHistoryStack.push(mDataMap.get(map.get(KEY_TAG)));
                    mAdapter = new SimpleAdapter(activity, mDataList, android.R.layout.simple_list_item_1, new String[]{KEY_TITLE},
                            new int[]{android.R.id.text1});
                    mListView.setAdapter(mAdapter);
                } else {
                    showToast("内容还没有准备好，请仔细检查后再进行操作！");
                }
            } else { // 如果没有下级,直接打开页面
                intent = new Intent(activity, (Class<?>) map.get(KEY_ACTIVITY));
                startActivity(intent);
            }
        });
        mListView.setOnItemLongClickListener((parent, view, position, id) -> {
            Map<String, Object> map = mDataList.get(position);
            if (map.get(KEY_ACTIVITY) == RxAndroidActivity.class) {
                intent = new Intent(activity, ShowPhoneDBListActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    /** 初始化一级数据 */
    private void initDataList() {

        // 初始化四大组件数据
        addFirstLevelData("四大组件", true, TAG_FOUR_COMPONENTS, null);

        // 初始化自定义视图数据
        addFirstLevelData("自定义 View", true, TAG_CUSTOM_VIEW, null);

        // 初始化RxAndroid数据
        addFirstLevelData("RxAndroid", true, TAG_RX_ANDROID, null);

        // 初始化Retrofit数据
        addFirstLevelData("Retrofit", true, TAG_RETROFIT, null);

        // 初始化Databinding数据
        addFirstLevelData("Databinding", true, TAG_DATABINDING, null);

        // 初始化RecyclerView数据
        addFirstLevelData("RecyclerView", true, TAG_RECYCLERVIEW, null);

        // 初始化ViewPager数据
        addFirstLevelData("ViewPager", true, TAG_VIEWPAGER, null);

        // 初始化 动画 数据
        addFirstLevelData("动画", true, TAG_ANIMATION, null);

        // 初始化内容提供器数据
//        addFirstLevelData("内容提供器", true, TAG_CONTENT_PROVIDER, null);

        // 初始化短信数据
        addFirstLevelData("短信", true, TAG_SMS, null);

        // 初始化代码结构数据
        addFirstLevelData("代码结构", true, TAG_ARCHITECTURE, null);
    }

    private void addFirstLevelData(String title, boolean hasChild, String keyTag, Object activity) {
        Map<String, Object> map = new HashMap<>();
        map.put(KEY_TITLE, title);
        map.put(KEY_HAS_CHILD, hasChild);
        map.put(KEY_TAG, keyTag);
        map.put(KEY_ACTIVITY, activity == null ? "" : (Class<?>)activity);
        mDataList.add(map);
    }

    private void addOtherLevelData(List<Map<String, Object>> list, String title, boolean hasChild, String keyTag, Object activity) {
        Map<String, Object> map = new HashMap<>();
        map.put(KEY_TITLE, title);
        map.put(KEY_HAS_CHILD, hasChild);
        map.put(KEY_TAG, keyTag);
        map.put(KEY_ACTIVITY, activity);
        list.add(map);
    }

    /** 初始化一级以下的数据 */
    private void initDataMap() {
        initFourComponentsDataMap();
        initServiceDataMap();
        initCustomViewDataMap();
        initRxAndroidDataMap();
        initRetrofitDataMap();
        initDatabindingDataMap();
        initRecyclerViewDataMap();
        initViewPagerDataMap();
        initAnimationDataMap();
        initContentProviderDataMap();
        initContentProviderContactDataMap();
        initSMSDataMap();
        initArchitectureDataMap();
    }

    /** 初始化四大组件二级数据 */
    private void initFourComponentsDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "Activity", true, TAG_ACTIVITY, null);
        addOtherLevelData(list, "服务", true, TAG_SERVICE, null);
        addOtherLevelData(list, "广播", true, TAG_BROADCAST, null);
        addOtherLevelData(list, "内容提供器", true, TAG_CONTENT_PROVIDER, null);

        mDataMap.put(TAG_FOUR_COMPONENTS, list);
    }

    /** 初始化自定义View二级数据 */
    private void initServiceDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "启动服务——Service", false, "", StartServiceActivity.class);
        addOtherLevelData(list, "绑定服务——Binder", false, "", BindServiceBinderActivity.class);

        mDataMap.put(TAG_SERVICE, list);
    }

    /** 初始化自定义View二级数据 */
    private void initCustomViewDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "基本图形绘制", false, "", BasicGraphicsActivity.class);
        addOtherLevelData(list, "自定义 View 的测量", false, "", MeasureModelActivity.class);
        addOtherLevelData(list, "自定义 TextView —— 双框", false, "", TextViewMultiBackgroundActivity.class);
        addOtherLevelData(list, "自定义 TextView —— 文本闪烁", false, "", TextViewFlickerActivity.class);
        addOtherLevelData(list, "自定义 TextView —— 顶部工具栏", false, "", TopBarActivity.class);
        addOtherLevelData(list, "自定义 View —— 圆弧比例图", false, "", ArcRatioActivity.class);
        addOtherLevelData(list, "自定义 View —— 音频条状图", false, "", AudioBarChartActivity.class);
        addOtherLevelData(list, "自定义 View —— 测试/复习", false, "", TestCustomViewActivity.class);

        mDataMap.put(TAG_CUSTOM_VIEW, list);
    }

    /** 初始化 RxAndroid 二级数据 */
    private void initRxAndroidDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "简单示例", false, "", RxAndroidActivity.class);
        addOtherLevelData(list, "创建操作", false, "", RxCreateActivity.class);

        mDataMap.put(TAG_RX_ANDROID, list);
    }

    /** 初始化 Retrofit 二级数据 */
    private void initRetrofitDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "根据 IP 获取地理相关信息（繁）", false, "", RetrofitTestActivity.class);
        addOtherLevelData(list, "获取 GitHub 某个仓库的贡献者列表", false, "", GetContributorsActivity.class);
        addOtherLevelData(list, "获取知乎最新消息列表", false, "", GetLatestActivity.class);

        mDataMap.put(TAG_RETROFIT, list);
    }

    /** 初始化 Retrofit 二级数据 */
    private void initDatabindingDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "创建时绑定", false, "", CreateAndAssignActivity.class);

        mDataMap.put(TAG_DATABINDING, list);
    }

    /** 初始化 RecyclerView 二级数据 */
    private void initRecyclerViewDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "简单使用", false, "", ContactActivity.class);
        addOtherLevelData(list, "下拉刷新", false, "", RefreshActivity.class);
        addOtherLevelData(list, "鸿洋博客示例", false, "", HongYangTutorialActivity.class);
        addOtherLevelData(list, "雄安列表", false, "", XampRVActivity.class);
        addOtherLevelData(list, "仿淘宝Grid", false, "", TaoBaoGridActivity.class);

        mDataMap.put(TAG_RECYCLERVIEW, list);
    }

    /** 初始化 RecyclerView 二级数据 */
    private void initViewPagerDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "仿微信首页", false, "", WeiChatVPActivity.class);
        addOtherLevelData(list, "仿推酷首页新闻列表", false, "", TuiCoolArticleActivity.class);

        mDataMap.put(TAG_VIEWPAGER, list);
    }

    /** 初始化 Animation 二级数据 */
    private void initAnimationDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "圆半径动态改变", false, "", RadiusChangedCircleActivity.class);
        addOtherLevelData(list, "滑动查看图片", false, "", SlidePicturesActivity.class);

        mDataMap.put(TAG_ANIMATION, list);
    }

    /** 初始化 ContentProvider 二级数据 */
    private void initContentProviderDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "日历内容提供器", false, "", CalendarProviderActivity.class);
        addOtherLevelData(list, "联系人内容提供器", true, TAG_CONTENT_PROVIDER_CONTACTS, null);
        addOtherLevelData(list, "通话记录内容提供器", false, "", CallsProviderActivity.class);

        mDataMap.put(TAG_CONTENT_PROVIDER, list);
    }

    /** 初始化 Contacts Provider 三级数据 */
    private void initContentProviderContactDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "初试", false, "", ContactsProviderActivity.class);
        addOtherLevelData(list, "查询联系人", false, "", ContactsSearchByNameActivity.class);

        mDataMap.put(TAG_CONTENT_PROVIDER_CONTACTS, list);
    }

    /** 初始化 SMS 二级数据 */
    private void initSMSDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "短信查询", false, "", SmsPVActivity.class);
        addOtherLevelData(list, "使用 ACTION_SENDTO 发送短信", false, "", SendIntentSendToSmsActivity.class);
        addOtherLevelData(list, "使用 ACTION_VIEW 发送短信", false, "", SendIntentViewSmsActivity.class);
        addOtherLevelData(list, "使用 SmsManager 发送短信发广播", false, "", SendSmsWithBroadcastBySmsManagerActivity.class);

        mDataMap.put(TAG_SMS, list);
    }

    /** 初始化 代码结构 二级数据 */
    private void initArchitectureDataMap() {
        List<Map<String, Object>> list = new ArrayList<>();

        addOtherLevelData(list, "登录示例", false, "", LoginActivity.class);

        mDataMap.put(TAG_ARCHITECTURE, list);
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
                new Handler().postDelayed(() -> ifExit = false, 2000);
            } else
                super.onBackPressed();
        }
    }
}
