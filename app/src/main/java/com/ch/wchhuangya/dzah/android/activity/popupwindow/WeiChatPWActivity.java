package com.ch.wchhuangya.dzah.android.activity.popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.ParamHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仿微信弹出菜单
 * Created by wchya on 2016/4/21.
 */
public class WeiChatPWActivity extends BaseActivity {

    private ImageView mWeichatAdd;

    private PopupWindow mPopupWindow;
    private List<Map<String, String>> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wei_chat_pw);

        init();
    }

    private void init() {
        mWeichatAdd = (ImageView) findViewById(R.id.weichat_add);
        View view = View.inflate(this, R.layout.common_listview, null);
        mPopupWindow = new PopupWindow(view, ParamHelper.getEqumentWidth(this) / 2, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        mPopupWindow.setAnimationStyle(R.style.PopupW_weichat_anim);
        ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setBackgroundResource(R.color.weichat_head_bg);
        listView.setDivider(new ColorDrawable(getResources().getColor(R.color.weichat_listview_divider)));
        listView.setDividerHeight(1);
        initData();
        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

        mWeichatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing())
                    mPopupWindow.dismiss();
                else
                    mPopupWindow.showAsDropDown(mWeichatAdd, -(ParamHelper.getEqumentWidth(WeiChatPWActivity.this) / 2 - mWeichatAdd.getWidth()), 1);
            }
        });
    }

    private void initData() {
        mList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        map.put("id", "chat");
        map.put("name", "发起群聊");
        mList.add(map);

        map = new HashMap<>();
        map.put("id", "add_friends");
        map.put("name", "添加朋友");
        mList.add(map);

        map = new HashMap<>();
        map.put("id", "scan");
        map.put("name", "扫一扫");
        mList.add(map);

        map = new HashMap<>();
        map.put("id", "pay");
        map.put("name", "收付款");
        mList.add(map);

        map = new HashMap<>();
        map.put("id", "feedback");
        map.put("name", "帮助与反馈");
        mList.add(map);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Map<String, String> getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Map<String, String> map = mList.get(position);
            convertView = View.inflate(getApplicationContext(), R.layout.wei_chat_item, null);
            convertView.setTag(position);
            TextView tv = (TextView) convertView.findViewById(R.id.weichat_item_tv);
            tv.setText(map.get("name"));
            ImageView iv = (ImageView) convertView.findViewById(R.id.weichat_item_iv);
            iv.setBackgroundResource(getImgResourceId(map.get("id")));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPopupWindow.isShowing())
                        mPopupWindow.dismiss();
                    Toast.makeText(getApplicationContext(), mList.get(Integer.parseInt(v.getTag().toString())).get("name"), Toast.LENGTH_LONG).show();
                }
            });
            return convertView;
        }
    }

    private int getImgResourceId(String id) {
        switch (id) {
            case "chat":
                return R.mipmap.weichat_chat;
            case "add_friends":
                return R.mipmap.weichat_add_friends;
            case "scan":
                return R.mipmap.weichat_scan;
            case "pay":
                return R.mipmap.weichat_pay;
            case "feedback":
                return R.mipmap.weichat_feedback;
        }
        return R.mipmap.weichat_chat;
    }
}
