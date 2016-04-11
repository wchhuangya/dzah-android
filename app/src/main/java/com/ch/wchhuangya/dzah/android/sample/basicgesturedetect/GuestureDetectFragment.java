package com.ch.wchhuangya.dzah.android.sample.basicgesturedetect;

/**
 * 一个包含主要示例描述的简单的启动activity，在工具条按钮上有些动作
 * and a few action bar buttons.
 * Created by wchya on 2015-10-12.
 */
public class GuestureDetectFragment extends SimpleActivityBase {

    /*@Bind(R.id.gesture_detect_msg_tv)
    TextView mMsgTv;
    @Bind(R.id.gesture_detect_fg)
    android.support.v4.app.Fragment mFg;
    @Bind(R.id.guesture_detect_ll)
    LinearLayout mLl;

    *//** 当前Fragment的名称 *//*
    public static final String CUR_FRAG_TAG = "GuestureDetectActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_detect);
        ButterKnife.bind(this);

        if(getSupportFragmentManager().findFragmentByTag(CUR_FRAG_TAG) == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            GuestureDetectFragment fragment = new GuestureDetectFragment();
            //transaction.add(fragment, CUR_FRAG_TAG);
            transaction.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    *//** Create a chain of targets that will receive log data *//*
    @Override
    public void initializeLogging() {
        // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        // On screen logging via a fragment with a TextView.
        LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());
        logFragment.getLogView().setTextAppearance(this, R.style.CustomLog_Log);
        logFragment.getLogView().setBackgroundColor(Color.WHITE);


        Log.i(TAG, "Ready");
    }*/
}
