package com.ch.wchhuangya.dzah.android.activity.mvp.main;

/**
 * Created by wchya on 2017-02-16 09:07
 */

public interface MainPresenter {
    /** onResume 方法 */
    void onResume();

    /** 列表元素点击方法 */
    void onItemClicked(int position);

    /** onDestroy 方法 */
    void onDestroy();
}
