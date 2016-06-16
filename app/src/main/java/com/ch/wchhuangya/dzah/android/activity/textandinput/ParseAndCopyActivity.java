package com.ch.wchhuangya.dzah.android.activity.textandinput;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

/**
 * 复制、粘贴功能小试
 * Created by wchya on 2016/6/13.
 */
public class ParseAndCopyActivity extends BaseActivity {

    private ClipboardManager mClipboardManager;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parse_copy);

        mTextView = (TextView) findViewById(R.id.parse_copy_tv);
        EditText editText = (EditText) findViewById(R.id.parse_copy_et);
        registerForContextMenu(mTextView);
        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 1, 1, "复制文本到剪贴板");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                ClipData clip = ClipData.newPlainText("测试文本", mTextView.getText());
                mClipboardManager.setPrimaryClip(clip);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
