package com.ch.wchhuangya.dzah.android.activity.mvp.main;

import android.os.Handler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wchya on 2017-02-16 09:08
 */

public class FindItemsInteractorImpl implements FindItemsInteractor {
    @Override
    public void findItems(OnFinishedListener listener) {
        new Handler().postDelayed(() -> listener.onFinished(createArrayList()), 2000);
    }

    private List<String> createArrayList() {
        return Arrays.asList(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8",
                "Item 9",
                "Item 10"
        );
    }
}
