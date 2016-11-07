package com.ch.wchhuangya.dzah.android.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ch.wchhuangya.dzah.android.BR;

/**
 * 观察者对象实现数据变化界面变化
 * Created by wchya on 16/11/4.
 */

public class UserObservableObject extends BaseObservable {
    private String firstName;
    private String lastName;

    public UserObservableObject(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
