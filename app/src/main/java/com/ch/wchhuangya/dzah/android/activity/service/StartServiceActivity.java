package com.ch.wchhuangya.dzah.android.activity.service;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.service.StartIntentService;
import com.ch.wchhuangya.dzah.android.service.StartService;

/**
 * 继承基本服务类的演示
 * Created by wchya on 2017-02-07 08:29
 */

public class StartServiceActivity extends BaseActivity {

    public static final String ACTIVITY_SERVICE_INTERACTIVE = "activity_service_interactive";
    public static final String KEY_NUM = "num";
    private TextView mResTv;
    private MyBroadcast mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_service);

        mResTv = (TextView) findViewById(R.id.show_res);
        registerBroadcast();

        /*Observable.interval(0, 1, TimeUnit.SECONDS)
                .filter(integer -> integer < 400 && integer % 3 == 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    integer -> mResTv.append(integer + "" + "\t\t\t\t")
                );*/
    }

    public void forClick(View view) throws PendingIntent.CanceledException {
        if (view.getId() == R.id.start_basic_service) {
            Intent i = new Intent(this, StartService.class);
            startService(i);
        } else if (view.getId() == R.id.stop_basic_service) {
            Intent i = new Intent(this, StartService.class);
            stopService(i);
        } else if (view.getId() == R.id.start_intent_service) {
            Intent i = new Intent(this, StartIntentService.class);
            startService(i);

//            PendingIntent pi = PendingIntent.getBroadcast(this, 0, )
//            pi.send();
        } else if (view.getId() == R.id.stop_intent_service) {
            showToast("IntentService 不用手动停止，干完活会自己停止的");
        }
    }

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTIVITY_SERVICE_INTERACTIVE);
        mReceiver = new MyBroadcast();
        registerReceiver(mReceiver, filter);
    }

    class MyBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTIVITY_SERVICE_INTERACTIVE.equals(intent.getAction())) {
//                int res = intent.getIntExtra("num", -1);
                long res = intent.getLongExtra("num", -1l);
                mResTv.append(res + "" + "\t\t\t\t");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
