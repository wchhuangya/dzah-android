package com.ch.wchhuangya.dzah.android.activity.mvp.main;

import android.content.Context;

import com.ch.wchhuangya.dzah.android.R;

import java.util.List;

/**
 * Created by wchya on 2017-02-16 09:07
 */

public class MainPresenterImpl implements MainPresenter, FindItemsInteractor.OnFinishedListener {

    private MainView mMainView;
    private FindItemsInteractor mInteractor;
    private Context mContext;

    public MainPresenterImpl(Context context, MainView mainView, FindItemsInteractor interactor) {
        mContext = context;
        mMainView = mainView;
        mInteractor = interactor;
    }

    @Override
    public void onFinished(List<String> items) {
        if (mMainView != null) {
            mMainView.setItems(items);
            mMainView.hideProgress();
        }
    }

    @Override
    public void onResume() {
        if (mMainView != null)
            mMainView.showProgress();

        mInteractor.findItems(this);
    }

    @Override
    public void onItemClicked(int position) {
        if (mMainView != null)
            mMainView.showMessage(String.format(mContext.getString(R.string.mvp_msg), position));
    }

    @Override
    public void onDestroy() {
        mMainView = null;
    }

    public MainView getMainView() {
        return mMainView;

    }
}