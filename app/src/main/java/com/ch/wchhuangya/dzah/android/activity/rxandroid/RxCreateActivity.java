package com.ch.wchhuangya.dzah.android.activity.rxandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.util.Constant;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * RxJava 之 Create 创建操作
 * Created by wchya on 16/10/28.
 */

public class RxCreateActivity extends BaseActivity {

    @Bind(R.id.rx_create_tv)
    TextView mRxCreateTv;

    private int number;
    private Subscriber mIntervalSubscribe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_create);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rx_create_create_btn)
    public void create(View view) {
        mRxCreateTv.setText("");
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 5; i++) {
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribe(integer -> mRxCreateTv.append("序列完成！"),
                throwable -> mRxCreateTv.append("出现错误：" + throwable.getMessage()),
                () -> mRxCreateTv.append("序列完成！"));
    }

    @OnClick(R.id.rx_create_defer_btn)
    public void defer(View view) {
        number = 10;
        mRxCreateTv.setText("");
        mRxCreateTv.append("当前 number 的值是：" + number + Constant.NEW_LINE);

        Observable deferObservable = Observable.defer(() -> Observable.just(number));

        number = 20;
        mRxCreateTv.append("number 重新赋值，值为：" + number + Constant.NEW_LINE);
        mRxCreateTv.append("订阅观察！" + Constant.NEW_LINE);

        deferObservable.subscribe(o -> {
            mRxCreateTv.append("观察者获取的值为：" + o);
        });
    }

    @OnClick(R.id.rx_create_ent_btn)
    public void emptyNeverThrow(View view) {
        mRxCreateTv.append("empty: 创建一个不发射任何数据但正常终止的 Observable！" + Constant.NEW_LINE);
        mRxCreateTv.append("never: 创建一个不发射数据，也不终止的 Observable！" + Constant.NEW_LINE);
        mRxCreateTv.append("throw: 创建一个不发射数据，以一个错误终止的 Observable！" + Constant.NEW_LINE);
    }

    @OnClick(R.id.rx_create_from_btn)
    public void from(View view) {
        Observable observable = Observable.from(new Integer[]{1, 2, 3, 4, 5, 6});
        observable.subscribe(integer -> {
                    mRxCreateTv.append("onNext: " + integer + Constant.NEW_LINE);
                },
                throwable -> {
                    mRxCreateTv.append("出错啦：" + throwable.toString() + Constant.NEW_LINE);
                },
        () -> mRxCreateTv.append("完成了！" + Constant.NEW_LINE));
    }

    @OnClick(R.id.rx_create_interval_btn)
    public void interval(View view) {
        if (mIntervalSubscribe == null) {
            Observable observable = Observable.interval(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
            mIntervalSubscribe = new Subscriber() {
                @Override
                public void onCompleted() {
                    mRxCreateTv.append("结束了！" + Constant.NEW_LINE);
                }

                @Override
                public void onError(Throwable e) {
                    mRxCreateTv.append("出错了：" + e.getMessage() + Constant.NEW_LINE);
                }

                @Override
                public void onNext(Object o) {
                    mRxCreateTv.append("onNext: " + o + Constant.NEW_LINE);
                }
            };
            observable.subscribe(mIntervalSubscribe);
        } else {
            mIntervalSubscribe.unsubscribe();
            mIntervalSubscribe = null;
        }
    }
}
