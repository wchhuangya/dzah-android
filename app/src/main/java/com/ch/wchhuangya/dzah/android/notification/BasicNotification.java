package com.ch.wchhuangya.dzah.android.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.ch.wchhuangya.dzah.android.activity.notification.BasicNotificationActivity;
import com.ch.wchhuangya.dzah.android.util.Constant;

/**
 * 基本布局的通知
 * Created by wchya on 2015-10-10.
 */
public class BasicNotification extends Notification {

    /**
     * 发起基本布局的Notification
     * @param context 上下文
     * @param bundle 键值对集合，包含了Notification的title、text、icon等信息
     */
    public static boolean sendBasicNotify(@NonNull Context context, @NonNull Bundle bundle) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext());

        if(-100 == bundle.getInt("small_icon", -100))
            return false;
        builder.setSmallIcon(bundle.getInt("small_icon"));
        if(null == bundle.getString("title"))
            return false;
        builder.setContentTitle(bundle.getString("title"));
        if(null == bundle.getString("text"))
            return false;
        builder.setContentText(bundle.getString("text"));

        // 创建一个指向某个activity的显式的intent对象
        Intent resIntent = new Intent(context, BasicNotificationActivity.class);
        resIntent.putExtra("title", bundle.getString("title"));
        resIntent.putExtra("text", bundle.getString("text"));
        resIntent.putExtra("small_icon", bundle.getInt("small_icon"));

        // TODO TaskStackBuilder对象把将要启动的activity放在一个假的后退栈中，这样做的目的是：当用户从activity返回时，会回到主屏幕，而不是应用
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context.getApplicationContext());
        // 添加后退栈
        stackBuilder.addParentStack(BasicNotificationActivity.class);
        // 把Intent添加到栈顶
        stackBuilder.addNextIntent(resIntent);

        // 获得一个PendingIntent包含整个后台堆栈
        PendingIntent resPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(resPendingIntent);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(Constant.NOTIFICATION_BASIC_ID, builder.build());
        return true;
    }
}
