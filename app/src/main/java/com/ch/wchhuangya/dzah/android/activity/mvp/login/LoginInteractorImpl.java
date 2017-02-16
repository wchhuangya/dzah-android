package com.ch.wchhuangya.dzah.android.activity.mvp.login;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by wchya on 2017-02-15 11:18
 */

public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {
        // 模拟登录。使用延迟。
        new Handler().postDelayed(() -> {
            boolean error =false;

            if (TextUtils.isEmpty(username)) {
                listener.onUsernameError();
                error = true;
                return;
            }

            if (TextUtils.isEmpty(password)) {
                listener.onPasswordError();
                error = true;
                return;
            }

            if (!error)
                listener.onSuccess();
        }, 2000);
    }
}
