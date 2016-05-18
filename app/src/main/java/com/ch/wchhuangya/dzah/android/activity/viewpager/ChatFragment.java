package com.ch.wchhuangya.dzah.android.activity.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.dzah.android.R;

/**
 * 聊天Fragmentation
 * Created by wchya on 2016/5/4.
 */
public class ChatFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_fragment, container, false);
    }
}
