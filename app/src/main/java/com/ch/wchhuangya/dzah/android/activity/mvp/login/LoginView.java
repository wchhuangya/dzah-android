package com.ch.wchhuangya.dzah.android.activity.mvp.login;

/**
 * 用于定义 View 应该做的事情
 * Created by wchya on 2017-02-15 10:55
 */

public interface LoginView {
    /** 显示进度条 */
    void showProgress();

    /** 隐藏进度条 */
    void hideProgress();

    /** 用户名输入错误时显示错误信息 */
    void setUsernameError();

    /** 密码输入错误时显示错误信息 */
    void setPasswordError();

    /** 跳转到首页 */
    void navigateToHome();
}
