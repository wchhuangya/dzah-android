package com.ch.wchhuangya.dzah.android.service;

import android.app.IntentService;
import android.content.Intent;

import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.HttpHelper;
import com.ch.wchhuangya.dzah.android.util.LogHelper;
import com.ch.wchhuangya.dzah.android.util.TimeHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * 外部联系人服务
 * Created by wchya on 2015-10-09.
 */
public class ContactsService extends IntentService {

    /**
     * TODO 使用IntentService时，必须有一个无参的构造器，并在构造器中调用父类构造器的实现，父类构造器的参数是worker线程的名称
     */
    public ContactsService(){
        super("ContactsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        LogHelper.d(ContactsService.class, "获取联系人数据开始   " + TimeHelper.getCurrentTime(null));
        try {
            InputStream is = HttpHelper.loadResource("http://www.ctnma.cn:8081/contactsUsers?SID=" + Constant.COMMON_USER_ID_VALUE + "&orgId=" + Constant.COMMON_ORG_ID_VALUE);
            ObjectMapper mapper = new ObjectMapper();
            String res = mapper.readValue(new InputStreamReader(new GZIPInputStream(is), "utf-8"), String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogHelper.d(ContactsService.class, "获取联系人数据完成   " + TimeHelper.getCurrentTime(null));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogHelper.d(ContactsService.class, "it's in the onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogHelper.d(ContactsService.class, "it's in the onStartCommand(), flags=" + flags + "|||startId" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogHelper.d(ContactsService.class, "it's in the onDestroy()");
    }
}
