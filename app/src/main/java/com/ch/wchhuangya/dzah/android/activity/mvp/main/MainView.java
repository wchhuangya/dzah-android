package com.ch.wchhuangya.dzah.android.activity.mvp.main;

import java.util.List;

/**
 * Created by wchya on 2017-02-16 09:07
 */

public interface MainView {
    /** 显示进度条 */
    void showProgress();

    /** 隐藏进度条 */
    void hideProgress();

    /** 给列表设置元素 */
    void setItems(List<String> items);

    /** 显示提示信息 */
    void showMessage(String message);
}
