package com.ch.wchhuangya.dzah.android.activity.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 雄安列表测试
 * Created by wchya on 2017-06-05 20:18
 */

public class XampRVActivity extends BaseActivity {

    @Bind(R.id.xamp_rv)
    RecyclerView mXampRv;

    private List<Map<String, String>> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_excise_xamp);
        ButterKnife.bind(this);

        initDatas();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXampRv.setLayoutManager(layoutManager);
        mXampRv.addItemDecoration(new XampItemDecoration(getResources().getDrawable(R.drawable.xamp_divider)));
        mXampRv.setAdapter(new MyAdapter());
    }

    private void initDatas() {
        Map<String, String> map = new HashMap<>();

        map.put("name", "河北彬彬服装有限公司");
        map.put("code", "8923");
        map.put("type", "股份有限公司");
        map.put("person", "王加斌");
        map.put("personNums", "543");
        map.put("awardsNums", "21");
        mDatas.add(map);

        map = new HashMap<>();
        map.put("name", "扬子公司");
        map.put("code", "121548848152");
        map.put("type", "内咨公司");
        map.put("person", "王刚");
        map.put("personNums", "500");
        map.put("awardsNums", "11");
        mDatas.add(map);

        map = new HashMap<>();
        map.put("name", "江北制造");
        map.put("code", "547844578");
        map.put("type", "股份有限公司");
        map.put("person", "李龙");
        map.put("personNums", "400");
        map.put("awardsNums", "1");
        mDatas.add(map);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(XampRVActivity.this).inflate(R.layout.rv_excise_xamp_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mNameTv.setText(mDatas.get(position).get("name"));
            holder.mTypeTv.setText(mDatas.get(position).get("type"));
            holder.mCodeTv.setText(mDatas.get(position).get("code"));
            holder.mPersonTv.setText(mDatas.get(position).get("person"));
            holder.mPersonNumsTv.setText(mDatas.get(position).get("personNums"));
            holder.mAwardsTv.setText(mDatas.get(position).get("awardsNums"));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView mNameTv;
            TextView mTypeTv;
            TextView mCodeTv;
            TextView mPersonTv;
            TextView mPersonNumsTv;
            TextView mAwardsTv;

            ViewHolder(View itemView) {
                super(itemView);
                mNameTv = (TextView) itemView.findViewById(R.id.xamp_name_tv);
                mCodeTv = (TextView) itemView.findViewById(R.id.xamp_code_tv);
                mTypeTv = (TextView) itemView.findViewById(R.id.xamp_type_tv);
                mPersonTv = (TextView) itemView.findViewById(R.id.xamp_person_tv);
                mPersonNumsTv = (TextView) itemView.findViewById(R.id.xamp_person_nums_tv);
                mAwardsTv = (TextView) itemView.findViewById(R.id.xamp_awards_nums_tv);
            }
        }
    }
}
