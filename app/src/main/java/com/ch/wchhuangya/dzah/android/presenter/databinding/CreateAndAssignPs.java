package com.ch.wchhuangya.dzah.android.presenter.databinding;

import android.view.View;
import android.widget.Toast;

import com.ch.wchhuangya.dzah.android.model.UserObservableField;
import com.ch.wchhuangya.dzah.android.model.UserObservableObject;

/**
 * CreateAndAssignActivity 中控件的事件处理
 * Created by wchya on 16/11/4.
 */

public class CreateAndAssignPs {

    /**
     * 改变 User 属性的值
     */
    public void onClickChangeValue(UserObservableObject user) {
        user.setFirstName("你点击了标题");
        user.setLastName("我的内容就发生了变化");
    }

    public void title2OnClickChangeValue(UserObservableField user) {
        user.firstName.set("你点击了标题");
        user.lastName.set("我的内容就发生了变化");
    }

    public void testLambda(View view) {
        Toast.makeText(view.getContext(), "测试 Lambda 的缩写语法", Toast.LENGTH_LONG).show();
    }
}
