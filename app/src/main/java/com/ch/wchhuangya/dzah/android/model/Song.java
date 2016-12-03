package com.ch.wchhuangya.dzah.android.model;

import android.databinding.ObservableField;

/**
 * Created by wchya on 2016-12-02 11:10
 */

public class Song {
    public ObservableField<String> order = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> lyrics = new ObservableField<>("");
    public ObservableField<String> tune = new ObservableField<>("");
    public ObservableField<String> arrangement = new ObservableField<>("");
    public ObservableField<String> state = new ObservableField<>("");
}
