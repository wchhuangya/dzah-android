package com.ch.wchhuangya.dzah.android.activity.canvas;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.components.TimelineView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 时间轴控件显示
 * Created by wchya on 2016/6/19.
 */
public class TimelineViewActivity extends BaseActivity {
    private ListView mListView;
    private List<Map<String, String>> mListData = new ArrayList<>();
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = new ListView(this);
        mListView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mListView.setBackgroundResource(R.color.white);
        mListView.setDivider(null);
        mListView.setCacheColorHint(getResources().getColor(R.color.transparent));
        setContentView(mListView);

        activity = this;
        init();
    }

    private void init() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "这次是我不经意的离开，成为我这许久不变的悲哀。想让你忘却愁绪忘记关怀，许琦这纷纷扰扰自由自在。\n\n\n那次是我不经意的离开，成为我这许久不变的悲哀。");
        mListData.add(map);

        map = new HashMap<>();
        map.put("title", "若不是因为你，我依然在风雨里，飘来荡去我早已经放弃。怎么相信爱情，我怎么拥有你，一生一世的心注定是为了你。");
        mListData.add(map);

        map = new HashMap<>();
        map.put("title", "只会流汗，不会流泪。只懂前进，不懂后退，只想尝到挑战的滋味。");
        mListData.add(map);

        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mListData != null ? mListData.size() : 0;
        }

        @Override
        public Map<String, String> getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Map<String, String> map = mListData.get(position);
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(activity, R.layout.canvas_timeline_view_item, null);
                viewHolder.tv = (TextView) convertView.findViewById(R.id.canvas_timeline_view_item_tv);
                viewHolder.tlv = (TimelineView) convertView.findViewById(R.id.canvas_timeline_view_item_timeline);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv.setText(map.get("title"));
            viewHolder.tlv.initLine(TimelineView.getTimeLineViewType(position, getCount()));
            convertView.setTag(viewHolder);

            return convertView;
        }

        private class ViewHolder {
            TextView tv;
            TimelineView tlv;
        }
    }
}
