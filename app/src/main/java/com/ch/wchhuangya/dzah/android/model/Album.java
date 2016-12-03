package com.ch.wchhuangya.dzah.android.model;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by wchya on 2016-12-02 11:06
 */

public class Album {
    public ObservableInt imgResId = new ObservableInt(0);
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("");
    public ObservableInt count = new ObservableInt(0);
}
