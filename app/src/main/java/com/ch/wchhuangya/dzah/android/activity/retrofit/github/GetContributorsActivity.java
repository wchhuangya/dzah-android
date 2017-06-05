package com.ch.wchhuangya.dzah.android.activity.retrofit.github;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.activity.retrofit.ServiceGenerate;
import com.ch.wchhuangya.dzah.android.model.Contributor;
import com.ch.wchhuangya.dzah.android.util.LogHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 显示 GitHub 某个仓库贡献者列表
 */
public class GetContributorsActivity extends BaseActivity {

    @Bind(R.id.get_contributors_tv)
    TextView mGetContributorsTv;
    @Bind(R.id.common_listview_search_ll)
    LinearLayout mCommonListviewSearchLl;
    @Bind(android.R.id.list)
    ListView mList;
    @Bind(R.id.common_listview_tv)
    TextView mCommonListviewTv;
    
    private SimpleAdapter mAdapter;
    private List<Contributor> mData;
    private List<Map<String, String>> mListMap = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_contributors);
        ButterKnife.bind(this);
        
        init();
    }

    private void init() {
        ServiceGenerate.createService(GitHubClient.class, 1)
                .contributors("square", "retrofit")
                .flatMap(new Func1<List<Contributor>, Observable<Contributor>>() {
                    @Override
                    public Observable<Contributor> call(List<Contributor> contributors) {
                        return Observable.from(contributors);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::generateList,
                        throwable -> LogHelper.e(GetContributorsActivity.class, "获取贡献者出错：" + throwable.getMessage()),
                        ()->{
                            mAdapter = new SimpleAdapter(activity, mListMap, android.R.layout.simple_list_item_1,
                                    new String[]{"contributor"}, new int[]{android.R.id.text1});
                            mList.setAdapter(mAdapter);
                            mList.setEmptyView(mCommonListviewTv);
                        }
                );
    }

    private void generateList(Contributor contributor) {
        Map<String, String> map = new HashMap<>();
        map.put("contributor", contributor.getLogin());
        mListMap.add(map);
    }
}
