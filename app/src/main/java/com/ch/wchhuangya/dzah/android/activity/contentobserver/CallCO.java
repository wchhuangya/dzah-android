package com.ch.wchhuangya.dzah.android.activity.contentobserver;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/**
 * Created by wchya on 16/10/14.
 */

public class CallCO extends ContentObserver {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public CallCO(Handler handler) {
        super(handler);
    }

    /**
     * 该方法在 API 16 以下调用
     * @param selfChange
     */
    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    /**
     * 该方法仅仅只能在 API 16 及以上才能被调用
     * @param selfChange
     * @param uri
     */
    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
    }
}
