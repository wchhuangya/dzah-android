package com.ch.wchhuangya.dzah.android.activity.retrofit.getipins;

import com.ch.wchhuangya.dzah.android.model.TaoBaoIPMsg;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wchya on 16/8/11.
 */
public interface GetIPIns {

    /** 获取IP详情 */
    @POST("/service/getIpInfo.php")
    Call<TaoBaoIPMsg> getIPJsonIns(@Query("ip") String ip);
}
