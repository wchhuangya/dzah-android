package com.ch.wchhuangya.dzah.android.service;

import android.app.IntentService;
import android.content.Intent;

import com.ch.wchhuangya.dzah.android.util.LogHelper;

/**
 * 收到电话时不想接,点击短信图标,系统会弹出常用语以供选择,选择某个常用语,就给拨打电话的人回复一条短信,内容就是选择的常用语。
 * 当然,可以自定义短信内容,这样,就会打开系统默认的短信应用,并定位到发送短信的界面
 * Created by wchya on 16/9/23.
 */

public class RespondViaMessageService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public RespondViaMessageService() {
        super("respond-via-message");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        LogHelper.i(RespondViaMessageService.class, "data: " + intent.getData());
    }
}
