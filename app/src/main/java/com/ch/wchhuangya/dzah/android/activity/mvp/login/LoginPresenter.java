package com.ch.wchhuangya.dzah.android.activity.mvp.login;

/**
 * 登录的 Presenter 接口
 * Created by wchya on 2017-02-15 11:09
 */

public interface LoginPresenter {
    /** 登录验证的方法 */
    void validateCredentials(String username, String password);

    /** Activity 销毁时调用的方法 */
    void onDestory();
}
