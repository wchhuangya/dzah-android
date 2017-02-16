package com.ch.wchhuangya.dzah.android.activity.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.activity.mvp.main.MainActivity;

/**
 * 登录页面， View 层，应该持有登录 Presenter 的引用
 * Created by wchya on 2017-02-15 10:57
 */

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    private ProgressBar mProgressBar;
    private EditText mUsername;
    private EditText mPassword;
    private LoginPreseneterImpl mPreseneter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_login);

        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(this);

        mPreseneter = new LoginPreseneterImpl(this);
    }

    @Override
    public void onClick(View view) {
        mPreseneter.validateCredentials(mUsername.getText().toString().trim(), mPassword.getText().toString().trim());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        mUsername.setError(getString(R.string.error_username));
    }

    @Override
    public void setPasswordError() {
        mPassword.setError(getString(R.string.error_password));
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        mPreseneter.onDestory();
        super.onDestroy();
    }
}
