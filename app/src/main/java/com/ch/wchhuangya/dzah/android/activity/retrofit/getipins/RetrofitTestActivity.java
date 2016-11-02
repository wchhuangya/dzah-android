package com.ch.wchhuangya.dzah.android.activity.retrofit.getipins;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.ch.wchhuangya.dzah.android.BaseActivity;
import com.ch.wchhuangya.dzah.android.R;
import com.ch.wchhuangya.dzah.android.model.IPIns;
import com.ch.wchhuangya.dzah.android.model.TaoBaoIPMsg;
import com.ch.wchhuangya.dzah.android.util.Constant;
import com.ch.wchhuangya.dzah.android.util.LogHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Retrofit测试
 * Created by wchya on 16/8/11.
 */
public class RetrofitTestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_test);

        findViewById(R.id.retrofit_test_btn).setOnClickListener(view -> {
            String input = ((EditText) findViewById(R.id.retrofit_test_et)).getText().toString().trim();
            GetIPIns getIPIns = ServerFactory.createServiceFactory(GetIPIns.class, Constant.RTF_BASE_URL);
            Call<TaoBaoIPMsg> call = getIPIns.getIPJsonIns(TextUtils.isEmpty(input) ? "61.178.98.65" : input);
            call.enqueue(new Callback<TaoBaoIPMsg>() {
                @Override
                public void onResponse(Call<TaoBaoIPMsg> call, Response<TaoBaoIPMsg> response) {
                    LogHelper.d(RetrofitTestActivity.class, response.body().toString());
                    IPIns ipIns = response.body().getData();
                    ((TextView)findViewById(R.id.retrofit_test_tv)).setText(TextUtils.concat("当前的IP是：", ipIns.getIp(), "。\n国家：", ipIns.getCountry(), "。\n省份：",
                            ipIns.getRegion(), "。\n市(县)：", ipIns.getCity(), "。\n运营商：", ipIns.getIsp()).toString());
                }

                @Override
                public void onFailure(Call<TaoBaoIPMsg> call, Throwable t) {
                    LogHelper.e(RetrofitTestActivity.class, t.getMessage());
                }
            });
        });
    }
}
