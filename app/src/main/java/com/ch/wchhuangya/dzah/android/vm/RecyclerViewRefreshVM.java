package com.ch.wchhuangya.dzah.android.vm;

import android.app.Activity;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.activity.recyclerview.RefreshActivity;
import com.ch.wchhuangya.dzah.android.adapter.RefreshAdapter;
import com.ch.wchhuangya.dzah.android.databinding.CommonRecyclerviewBinding;
import com.ch.wchhuangya.dzah.android.model.CommonRecyclerView;
import com.ch.wchhuangya.dzah.android.util.Constant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by wchya on 2016-11-30 22:01
 */

public class RecyclerViewRefreshVM extends BaseVM {
    private CommonRecyclerviewBinding mLocalBinding;
    private Subscription mSubscribe;
    private int section = 0;
    private CommonRecyclerView mRecyclerview;
    private RefreshAdapter mAdapter;
    private List<List<Map<String, Object>>> mJaysAllDatas = RefreshActivity.getJaysAllDatas();

    public RecyclerViewRefreshVM(Activity activity, Resources resources) {
        mActivity = activity;
        mResources = resources;
        mBinding = DataBindingUtil.setContentView(activity, R.layout.common_recyclerview);
        mLocalBinding = (CommonRecyclerviewBinding) mBinding;
        mRecyclerview = new CommonRecyclerView();
        mLocalBinding.setRecyclerview(mRecyclerview);
    }

    public void initData() {
        mLocalBinding.recyclerviewSrl.setEnabled(false);
        mRecyclerview.rvShow.set(false);
        mRecyclerview.mpbShow.set(true);
        mSubscribe = Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    fetchNewData();
                    mLocalBinding.recyclerviewSrl.setEnabled(true);
                }, throwable -> {
                    Log.e(Constant.DZAH_TAG, "initData: 加载数据出错——", throwable);
                });
    }

    private void fetchNewData() {
        mRecyclerview.rvShow.set(true);
        mRecyclerview.mpbShow.set(false);
//        mJaysAllDatas = RefreshActivity.getJaysAllDatas();
//        mAdapter.setData(mJaysAllDatas.get(section), 0, mJaysAllDatas.get(section).size());
        if (section == mJaysAllDatas.size())
            section = 0;
        mAdapter.setRefreshData(mJaysAllDatas.get(section++));
    }

    public void initSwipeRefreshLayout() {
        mLocalBinding.recyclerviewSrl.setColorSchemeResources(R.color.refresh_first, R.color.refresh_second, R.color.refresh_third);
        mLocalBinding.recyclerviewSrl.setOnRefreshListener(() -> {
            fetchNewData();
            mLocalBinding.recyclerviewSrl.setRefreshing(false);
        });
    }

    public void initRecyclerView() {
        mLocalBinding.recyclerviewRv.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mAdapter = new RefreshAdapter();
        mLocalBinding.recyclerviewRv.setAdapter(mAdapter);
    }

    @Override
    public void reset() {
        mActivity = null;
        mResources = null;
        mBinding = null;
    }

    @Override
    public void unsubscribe() {
        if (mSubscribe != null && !mSubscribe.isUnsubscribed()) {
            mSubscribe.unsubscribe();
        }
    }
}
