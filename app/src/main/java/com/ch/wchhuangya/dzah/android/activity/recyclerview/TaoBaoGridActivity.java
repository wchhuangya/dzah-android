package com.ch.wchhuangya.dzah.android.activity.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wchya on 2017-06-06 20:38
 */

public class TaoBaoGridActivity extends BaseActivity {

    @Bind(R.id.xamp_rv)
    RecyclerView mRv;
    private List<String> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_excise_xamp);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mDatas = new ArrayList<>();
        mDatas.add("这是头部");
        mDatas.add("第一个");
        mDatas.add("第二个");
        mDatas.add("第三个");
        mDatas.add("第四个");
        mDatas.add("第五个");
        mDatas.add("第六个");
        mDatas.add("第七个");
        mDatas.add("第八个");

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0)
                    return 3;
                else
                    return 1;
            }
        });
        mRv.setLayoutManager(layoutManager);
        mRv.addItemDecoration(new TaoBaoItemDecoration(this, 8, 3));
        mRv.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(TaoBaoGridActivity.this).inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return 1;
            else
                return 2;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView mTv;

            public ViewHolder(View itemView) {
                super(itemView);
                mTv = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
