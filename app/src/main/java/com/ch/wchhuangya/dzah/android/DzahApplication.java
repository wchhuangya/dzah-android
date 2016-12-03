package com.ch.wchhuangya.dzah.android;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

import com.ch.wchhuangya.dzah.android.receiver.WakeLockReceiver;
import com.ch.wchhuangya.dzah.android.service.CallsService;
import com.ch.wchhuangya.dzah.android.service.SmsService;
import com.ch.wchhuangya.dzah.android.util.ActivityHelper;
import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.FileHelper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tencent.android.tpush.XGPushConfig;

import java.io.File;

/**
 * Application
 * Created by wchya on 2015-09-24.
 */
public class DzahApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Bmob.initialize(this, "1ec4aa2ceae3998de0982fe49ff6659e");
        XGPushConfig.enableDebug(this, Constant.DEBUG_SWITCH);

        if (!ActivityHelper.isServiceRunning(getApplicationContext(), SmsService.class.getName())) {
            Intent intent = new Intent(getApplicationContext(), SmsService.class);
            startService(intent);
        }

        if (!ActivityHelper.isServiceRunning(getApplicationContext(), CallsService.class.getName())) {
            Intent intent = new Intent(getApplicationContext(), CallsService.class);
            startService(intent);
        }

        WakeLockReceiver receiver = new WakeLockReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiver, filter);

        FileHelper.initDir();

        File cacheDirectory = StorageUtils.getCacheDirectory(this);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .showImageForEmptyUri(R.mipmap.pic_default)
                .showImageOnFail(R.mipmap.pic_default)
                .showImageOnLoading(R.mipmap.pic_default)
                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiskCache(cacheDirectory))
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
