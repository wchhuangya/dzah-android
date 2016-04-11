package com.ch.wchhuangya.dzah.android.components;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.LogHelper;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;

/**
 * 信鸽
 * Created by wchya on 2015-08-17.
 */
public class XGPush {

    /**
     * 信鸽注册
     * @param context 上下文
     * @param isNeedFeedback 是否需要结果反馈，true-需要，false-不需要；
     * @return
     */
    public static void registerPush(final Context context, boolean isNeedFeedback) {
        if(isNeedFeedback){
            XGPushManager.registerPush(context, new XGIOperateCallback() {
                @Override
                public void onSuccess(Object o, int i) {
                    SharedPreferences sp = context.getSharedPreferences(Constant.SP_NAME_COMPONENTS, Context.MODE_PRIVATE);
                    LogHelper.d(XGPush.class, "信鸽注册成功，token为：" + o.toString());
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(Constant.CPN_XPUSH_TOKEN, o.toString());
                    editor.commit();
                }

                @Override
                public void onFail(Object o, int i, String s) {
                    LogHelper.d(XGPush.class, "信鸽注册失败：" + o.toString() + "|||" + s);
                    View view = View.inflate(context, R.layout.common_toast, null);
                    Toast toast = new Toast(context);
                    toast.setView(view);
                    ((LinearLayout)view.findViewById(R.id.common_toast_ll)).setBackgroundResource(R.drawable.common_blue_btn_normal);
                    ((TextView)view.findViewById(R.id.common_toast_tv)).setText("信鸽注册失败");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        } else
            XGPushManager.registerPush(context);
    }

    /**
     * 信鸽注册
     * @param context 上下文
     * @param account 要绑定的账号
     * @param isNeedFeedback 是否需要结果反馈，true-需要，false-不需要；
     * @return
     */
    public static void registerPush(final Context context, final String account, boolean isNeedFeedback) {
        if(isNeedFeedback){
            XGPushManager.registerPush(context, new XGIOperateCallback() {
                @Override
                public void onSuccess(Object o, int i) {
                    SharedPreferences sp = context.getSharedPreferences(Constant.SP_NAME_COMPONENTS, Context.MODE_PRIVATE);
                    LogHelper.d(XGPush.class, "注册成功，绑定的账号为：" + account + "，token为：" + o.toString());
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(Constant.CPN_XPUSH_TOKEN, o.toString());
                    editor.putString(Constant.CPN_XPUSH_ACCOUNT, account);
                    editor.commit();
                }

                @Override
                public void onFail(Object o, int i, String s) {
                    LogHelper.d(XGPush.class, "信鸽注册失败：" + o.toString() + "|||" + s);
                    View view = View.inflate(context, R.layout.common_toast, null);
                    Toast toast = new Toast(context);
                    toast.setView(view);
                    ((LinearLayout)view.findViewById(R.id.common_toast_ll)).setBackgroundResource(R.drawable.common_blue_btn_normal);
                    ((TextView)view.findViewById(R.id.common_toast_tv)).setText("信鸽注册失败");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        } else
            XGPushManager.registerPush(context);
    }
}
