package com.ch.wchhuangya.dzah.android.activity.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.databinding.CreateAndAssignBinding;
import com.ch.wchhuangya.dzah.android.model.Echo;
import com.ch.wchhuangya.dzah.android.model.UserObservableField;
import com.ch.wchhuangya.dzah.android.model.UserObservableObject;
import com.ch.wchhuangya.dzah.android.model.UserTest;
import com.ch.wchhuangya.dzah.android.presenter.databinding.CreateAndAssignPs;

import java.util.Arrays;

/**
 * 测试创建即指定值的数据绑定
 * Created by wchya on 16/11/4.
 */

public class CreateAndAssignActivity extends BaseActivity {
    private UserObservableObject user1 = new UserObservableObject("firstName", "lastName");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CreateAndAssignBinding binding = DataBindingUtil.setContentView(this, R.layout.create_and_assign);

        binding.setUser(new UserTest("fistName", "lastName"));
        binding.setUser1(user1);

        UserObservableField user2 = new UserObservableField();
        user2.firstName.set("firstName");
        user2.lastName.set("lastName");
        binding.setUser2(user2);
        binding.setTitleList(Arrays.asList("没有观察者", "继承 BaseObservable (点我试试看)", "使用 ObservableField (点我试试看)"));
        binding.setPs(new CreateAndAssignPs());
        Echo echo = new Echo();
        echo.text = "haha";
        binding.setEcho(echo);
    }
}
