package com.ch.wchhuangya.dzah.android.activity.files;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 外部存储操作示例
 * Created by wchya on 2016/4/12.
 */
public class ExternalStorageActivity extends BaseActivity {

    @Bind(R.id.public_tv)
    TextView mPublicTv;
    @Bind(R.id.private_tv)
    TextView mPrivateTv;
    @Bind(R.id.public_type_sp)
    Spinner mPublicTypeSp;
    @Bind(R.id.private_type_sp)
    Spinner mPrivateTypeSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_storage);
        ButterKnife.bind(this);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.external_storage_dir_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPublicTypeSp.setAdapter(adapter);
        mPrivateTypeSp.setAdapter(adapter);
    }

    @OnClick(R.id.public_btn)
    public void getPublicPath(View view) {
        String type = getDirType(mPublicTypeSp.getSelectedItem().toString());
        if (type == null) {
            showToast("无法获取公共根目录，请重新选择子目录!", 1);
        } else {
            File file = Environment.getExternalStoragePublicDirectory(type);
            mPublicTv.setText(file.getAbsolutePath());
        }
    }

    @OnClick(R.id.private_btn)
    public void getPrivatePath(View view) {
        File file = getExternalFilesDir(getDirType(mPrivateTypeSp.getSelectedItem().toString()));
        mPrivateTv.setText(file.getAbsolutePath());
    }

    private String getDirType(String selected) {
        String type = "";
        switch (selected) {
            case "闹钟":
                type = Environment.DIRECTORY_ALARMS;
                break;
            case "相机":
                type = Environment.DIRECTORY_DCIM;
                break;
            case "文档":
                type = Environment.DIRECTORY_DOCUMENTS;
                break;
            case "下载":
                type = Environment.DIRECTORY_DOWNLOADS;
                break;
            case "电影":
                type = Environment.DIRECTORY_MOVIES;
                break;
            case "音乐（声音）":
                type = Environment.DIRECTORY_MUSIC;
                break;
            case "通知（声音）":
                type = Environment.DIRECTORY_NOTIFICATIONS;
                break;
            case "图片":
                type = Environment.DIRECTORY_PICTURES;
                break;
            case "明信片（声音）":
                type = Environment.DIRECTORY_PODCASTS;
                break;
            case "铃声（声音）":
                type = Environment.DIRECTORY_RINGTONES;
                break;
            case "根目录":
                type = null;
                break;
        }
        return type;
    }
}
