package com.ch.wchhuangya.dzah.android.activity.files;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 内部存储
 * Created by wchya on 2016/4/11.
 */
public class InternalStorageActivity extends BaseActivity {

    @Bind(R.id.root_tv)
    TextView mRootTv;
    @Bind(R.id.cache_tv)
    TextView mCacheTv;
    @Bind(R.id.temp_tv)
    TextView mTempTv;
    @Bind(R.id.create_et)
    EditText mCreateEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal_storage);
        ButterKnife.bind(this);

        activity = this;
    }

    @OnClick(R.id.root_btn)
    public void getRootPath(View view) {
        File file = getFilesDir();
        mRootTv.setText(file.getAbsolutePath());
    }

    @OnClick(R.id.cache_btn)
    public void getCachePath(View view) {
        File file = getCacheDir();
        mCacheTv.setText(file.getAbsolutePath());
    }

    @OnClick(R.id.create_btn)
    public void createFile(View view) {
        File file = new File(activity.getCacheDir(), "tt.txt");
        FileOutputStream fos = null;
        try {
            String content = mCreateEt.getText().toString().trim();
            fos = new FileOutputStream(file);
            fos.write(TextUtils.isEmpty(content) ? "hello file".getBytes() : content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.temp_btn)
    public void createTempFile(View view) {
        try {
            FileOutputStream fos = new FileOutputStream(File.createTempFile("tttt", null));
            fos.write("听见你说，朝阳起又落……".getBytes());
            fos.close();
            String[] arr_names = activity.getCacheDir().list();
            for (String s : arr_names) {
                mTempTv.append(s + "  |||  ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
