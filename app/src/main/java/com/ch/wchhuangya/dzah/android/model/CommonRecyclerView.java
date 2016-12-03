package com.ch.wchhuangya.dzah.android.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * Created by wchya on 2016-11-30 22:10
 */

public class CommonRecyclerView {
    public ObservableBoolean rvShow = new ObservableBoolean(false);
    public ObservableBoolean mpbShow = new ObservableBoolean(true);
    public ObservableField<String> tips = new ObservableField<>("暂无数据");
}
