package com.ch.wchhuangya.dzah.android.activity.retrofit.zhihu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.activity.retrofit.ServiceGenerate;
import com.ch.wchhuangya.dzah.android.model.ZhiHuLatest;
import com.ch.wchhuangya.dzah.android.util.LogHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GetLatestActivity extends BaseActivity {

    @Bind(R.id.common_listview_search_ll)
    LinearLayout mCommonListviewSearchLl;
    @Bind(android.R.id.list)
    ListView mList;
    @Bind(R.id.common_listview_tv)
    TextView mCommonListviewTv;

    private List<Map<String, String>> mData = new ArrayList<>();
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhi_hu_get_latest);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mCommonListviewSearchLl.setVisibility(View.GONE);

        ServiceGenerate.createService(ZhiHuClient.class, 1, "http://news-at.zhihu.com/")
                .latest()
                .flatMap(new Func1<ZhiHuLatest, Observable<ZhiHuLatest.TopStoriesBean>>() {
                    @Override
                    public Observable<ZhiHuLatest.TopStoriesBean> call(ZhiHuLatest zhiHuLatest) {
                        return Observable.from(zhiHuLatest.getTop_stories());
                    }
                })
                .map(this::getSingleMap)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(this::show)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        stringStringMap -> mData.add(stringStringMap),
                        throwable -> LogHelper.e(GetLatestActivity.class, "获取知乎最新数据失败：" + throwable.getMessage()),
                        this::showListData
                );

    }

    private void show() {
        progressDialog = ProgressDialog.show(activity, "提示", "正在加载数据，请耐心等待……", true, false);
    }

    private Map<String, String> getSingleMap(ZhiHuLatest.TopStoriesBean topStoriesBean) {
        Map<String, String> map = new HashMap<>();
        map.put("topStoriesBean", topStoriesBean.getTitle());
        return map;
    }

    private void showListData() {
        mAdapter = new SimpleAdapter(activity, mData, android.R.layout.simple_list_item_1,
                new String[]{"topStoriesBean"}, new int[]{android.R.id.text1});
        mList.setAdapter(mAdapter);
        mList.setEmptyView(mCommonListviewTv);
        if (progressDialog != null)
            progressDialog.dismiss();
    }
}
