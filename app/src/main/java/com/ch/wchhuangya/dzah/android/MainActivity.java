package com.ch.wchhuangya.dzah.android;

import android.content.Intent;
import android.os.Bundle;

import com.ch.wchhuangya.dzah.android.activity.canvas.CalendarViewActivity;
import com.ch.wchhuangya.dzah.android.activity.retrofit.getipins.RetrofitTestActivity;
import com.ch.wchhuangya.dzah.android.components.XGPush;

/**
 * 主页
 * Created by wchya on 2015-08-17.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        activity = this;
        // 查看信鸽是否注册过，已经注册则什么都不干，没有注册则注册信鸽
        //if(SharedPreferencesHelper.getString(activity, Constant.SP_NAME_COMPONENTS, Constant.CPN_XPUSH_TOKEN) == null)
            XGPush.registerPush(activity, true);

        Intent it = new Intent(activity, RetrofitTestActivity.class);
//        it.putExtra("msg", "hahahahhahahahahha");
//        startService(it);

//        intent = new Intent(activity, SmsPVActivity.class);
        startActivity(it);
    }
}
