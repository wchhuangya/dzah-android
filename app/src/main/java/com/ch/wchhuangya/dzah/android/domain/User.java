package com.ch.wchhuangya.dzah.android.domain;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * databinding使用的实体
 * Created by wchya on 2016/5/30.
 */
public class User {
    public ObservableField<String> userName = new ObservableField<String>();
    public ObservableField<String> userPwd = new ObservableField<String>();
    public ObservableBoolean isShowDel = new ObservableBoolean();
}
