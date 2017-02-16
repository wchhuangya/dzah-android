package com.ch.wchhuangya.dzah.android.activity.mvp.login;

/**
 * Created by wchya on 2017-02-15 11:14
 */

public interface LoginInteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

    /** 登录方法 */
    void login(String username, String password, OnLoginFinishedListener listener);
}
