package com.ch.wchhuangya.dzah.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

/**
 * 所有Activity的基类
 * Created by wchya on 2015-08-13.
 */
public class BaseActivity extends FragmentActivity {
    /** 当前的Activity */
    protected Activity activity;
    /** 公用的Intent对象，在使用前请初始化 */
    protected Intent intent;
    /** 等待动画 */
    protected ProgressDialog progressDialog;
    /** 公用的错误信息变量 */
    protected String msg;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 自定义的Toast显示
     * @param msg 要显示的信息
     * @param objects 持续时间,0-短时间，1-长时间,不填写,长时间
     */
    protected void showToast(String msg, Object...objects){
        int resId = R.drawable.common_blue_btn_normal;
        View view = View.inflate(this, R.layout.common_toast, null);
        Toast toast = new Toast(this);
        toast.setView(view);
        view.findViewById(R.id.common_toast_ll).setBackgroundResource(resId);
        ((TextView)view.findViewById(R.id.common_toast_tv)).setText(msg);
        int time = -1;
        if (objects.length < 1)
            time = Toast.LENGTH_LONG;
        else {
            int realTime = (int) objects[0];
            time = realTime == 1 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        }
        toast.setDuration(time);
        toast.show();
    }

    /**
     * 显示AlterDialog对话框
     * @param btnText 对话框的按钮文本，传递顺序是：positive、negative、neutral（至少得有一个），如果没有，就传null，null只能出现在末尾
     * @param clickListeners 对话框按钮点击的监听事件，与btnText的顺序、规则一致且对应，即一个btnText对应着一个clickListeners
     * @param title 对话框的标题文本，如果传null，且默认显示“对话框”
     * @param iconResId 对话框图标资源的ID，如果没有，传-1
     * @param message 要显示的对话框内容，如果传null，则必须设置contentView参数
     * @param contentView 要显示的自定义的内容视图
     */
    protected void showDialogs(String[] btnText, DialogInterface.OnClickListener[] clickListeners, String title, int iconResId, String message, View contentView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(null != title)
            builder.setTitle(title);
        else
            builder.setTitle("对话框");
        if(-1 != iconResId)
            builder.setIcon(iconResId);
        if(!TextUtils.isEmpty(message))
            builder.setMessage(message);
        else
            builder.setView(contentView);
        for(int i = 0; i < btnText.length; i++) {
            if(null != btnText[i]){
                switch (i){
                    case 0:
                        builder.setPositiveButton(btnText[i], clickListeners[i]);
                        break;
                    case 1:
                        builder.setNegativeButton(btnText[i], clickListeners[i]);
                        break;
                    case 2:
                        builder.setNeutralButton(btnText[i], clickListeners[i]);
                        break;
                }
            } else
                break;
        }
        builder.show();
    }

    /**
     * 显示等待动画
     * @param title 标题
     * @param content 内容
     * @param cancelable 是否可以取消
     */
    protected void showProgressDialog(@NonNull String title, @NonNull String content, boolean cancelable) {
        progressDialog = ProgressDialog.show(activity, "", content, true, cancelable);
    }

    /** 取消等待对话 */
    protected void cancelProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    /**
     * 显示错误信息。如果公用的变量msg有值，则显示msg的值；如果没值，则显示customMsg的值
     * @param customMsg 自定义的错误信息
     */
    protected void showErrorMsg(String customMsg) {
        if (!TextUtils.isEmpty(msg))
            showToast(msg);
        else
            showToast(customMsg);
    }
}
