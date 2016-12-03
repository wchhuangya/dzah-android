package com.ch.wchhuangya.dzah.android.vm;

import android.app.Activity;
import android.content.res.Resources;
import android.databinding.ViewDataBinding;

/**
 * Created by wchya on 2016-11-30 22:02
 */

public abstract class BaseVM {
    public ViewDataBinding mBinding;
    public Resources mResources;
    public Activity mActivity;

    public abstract void reset();
    public abstract void unsubscribe();
}
