package com.ch.wchhuangya.dzah.android.activity.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.databinding.DbHelloWorldBinding;
import com.ch.wchhuangya.dzah.android.model.User;

/**
 * DataBinding学习
 * Created by wchya on 2016/5/30.
 */
public class HelloWorldActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbHelloWorldBinding binding = DataBindingUtil.setContentView(this, R.layout.db_hello_world);

        User user = new User();
        binding.setUser(user);
        user.userName.set("second_name");
        user.userPwd.set("second_pwd");
        user.isShowDel.set(true);


    }
}
