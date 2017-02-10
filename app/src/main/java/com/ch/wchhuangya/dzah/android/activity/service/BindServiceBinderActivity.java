package com.ch.wchhuangya.dzah.android.activity.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.service.BindServiceBinderService;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 绑定服务之同步通讯录
 * Created by wchya on 2017-02-09 16:37
 */

public class BindServiceBinderActivity extends BaseActivity {

    @Bind(R.id.binder_res_tv)
    TextView mBinderResTv;
    private BindServiceBinderService.MyBinder mIBinder;
    private BindServiceBinderService mService;
    private boolean bindState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_service_binder);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.binder_bind_btn)
    public void bindBtn(View view) {
        mBinderResTv.append("\n" + "绑定服务");
        bindService();
    }

    private void bindService() {
        bindService(new Intent(this, BindServiceBinderService.class), mServiceConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinderResTv.append("\n" + "客户端收到连接成功的消息：" + TimeHelper.getCurrentTime("HH:mm"));
            mIBinder = (BindServiceBinderService.MyBinder) iBinder;
            mService = mIBinder.getService();
            bindState = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBinderResTv.append("\n" + "服务失去连接：" + TimeHelper.getCurrentTime("HH:mm"));
        }
    };

    @OnClick(R.id.binder_get_res_btn)
    public void getResBtn(View view) {
        if (bindState) {
            mBinderResTv.append("\n" + mService.printInfo());
        }
    }

    @OnClick(R.id.binder_unbinder_btn)
    public void unbindBtn(View view) {
        mBinderResTv.append("\n" + "解绑服务");
    }

    @Override
    protected void onStop() {
        super.onStop();
        bindState = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
