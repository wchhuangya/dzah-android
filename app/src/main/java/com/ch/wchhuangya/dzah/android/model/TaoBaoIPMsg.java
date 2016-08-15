package com.ch.wchhuangya.dzah.android.model;

import java.io.Serializable;

/**
 * 淘宝获取IP接口返回json的数据结构
 * Created by wchya on 16/8/11.
 */
public class TaoBaoIPMsg implements Serializable {
    private String code;
    private IPIns data;

    public IPIns getData() {
        return data;
    }

    public void setData(IPIns data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return getCode() + "," + getData().getArea() + "," + getData().getCity() + "," +
                getData().getCountry() + "," + getData().getRegion() + "," + getData().getIsp() + "," + getData().getIp();
    }
}
