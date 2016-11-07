package com.ch.wchhuangya.dzah.android.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * databinding使用的实体
 * Created by wchya on 2016/5/30.
 */
public class User {
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> userPwd = new ObservableField<>();
    public ObservableBoolean isShowDel = new ObservableBoolean();
}
