package com.ch.wchhuangya.dzah.android.activity.mvp.main;

import java.util.List;

/**
 * Created by wchya on 2017-02-16 09:08
 */

public interface FindItemsInteractor {
    interface OnFinishedListener {
        void onFinished(List<String> items);
    }

    void findItems(OnFinishedListener listener);
}
