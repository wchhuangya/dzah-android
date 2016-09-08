package com.ch.wchhuangya.dzah.android.activity.rxandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RxAndroid 学习
 * Created by wchya on 16/9/6.
 */
public class RxAndroidActivity extends BaseActivity {
    private Button mBindObverser1Btn;
    private Button mBindObverser2Btn;
    private Button mOutputArrayBtn;
    private Button mOutputSquareBtn;
    private Button mOperatorChainBtn;
    private TextView mResTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_android);

        init();
    }

    private void init() {
        mBindObverser1Btn = (Button) findViewById(R.id.rx_android_bind_observer1_btn);
        mBindObverser2Btn = (Button) findViewById(R.id.rx_android_bind_observer2_btn);
        mOutputArrayBtn = (Button) findViewById(R.id.rx_android_output_array_btn);
        mOutputSquareBtn = (Button) findViewById(R.id.rx_android_output_square_btn);
        mOperatorChainBtn = (Button) findViewById(R.id.rx_android_operator_chain_btn);
        mResTv = (TextView) findViewById(R.id.rx_android_res_tv);

        final Observable<String> observable = Observable.just("Hello");
        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                mResTv.setText("方法一: " + s);
            }
        };
        final Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                mResTv.setText("方法二: " + s);
            }
        };
        final Observable<Integer> outputArrayObservable = Observable.from(new Integer[]{8, 12, 31, 4, 3, 283});

        mBindObverser1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observable.subscribe(observer);
            }
        });
        mBindObverser2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observable.subscribe(action1);
            }
        });
        mOutputArrayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mResTv.setText("");
                outputArrayObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mResTv.append("  " + integer);
                    }
                });
            }
        });
        mOutputSquareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mResTv.setText("");
                outputArrayObservable.map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * integer;
                    }
                }).subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mResTv.append("  " + integer);
                    }
                });
            }
        });
        mOperatorChainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputArrayObservable.skip(2).filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
                    }
                }).subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mResTv.setText("  " + integer);
                    }
                });
            }
        });
    }
}
