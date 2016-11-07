package com.ch.wchhuangya.dzah.android.model;

import android.databinding.ObservableField;

/**
 * 使用 ObservableField 类型的数据使界面变化
 * Created by wchya on 2016-11-04 19:54
 */

public class UserObservableField {
    public ObservableField<String> firstName = new ObservableField<>();
    public ObservableField<String> lastName = new ObservableField<>();
}
