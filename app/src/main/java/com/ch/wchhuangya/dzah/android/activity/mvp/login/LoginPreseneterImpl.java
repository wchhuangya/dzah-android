package com.ch.wchhuangya.dzah.android.activity.mvp.login;

/**
 * Created by wchya on 2017-02-15 11:12
 */

public class LoginPreseneterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView mLoginView;
    private LoginInteractor mLoginInteractor;

    public LoginPreseneterImpl(LoginView loginView) {
        mLoginView = loginView;
        mLoginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void onUsernameError() {
        if (mLoginView != null) {
            mLoginView.setUsernameError();
            mLoginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (mLoginView != null) {
            mLoginView.setPasswordError();
            mLoginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (mLoginView != null)
            mLoginView.navigateToHome();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (mLoginView != null)
            mLoginView.showProgress();

        mLoginInteractor.login(username, password, this);
    }

    @Override
    public void onDestory() {
        mLoginView = null;
    }
}
