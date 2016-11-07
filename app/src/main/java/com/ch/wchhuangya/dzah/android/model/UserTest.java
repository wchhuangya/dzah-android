package com.ch.wchhuangya.dzah.android.model;

/**
 * 用户测试，测试没有构造方法，没有 getter setter，不使用 Observable，是否可以
 * Created by wchya on 16/11/4.
 */

public class UserTest {
    private String firstName;
    private String lastName;

    public UserTest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
