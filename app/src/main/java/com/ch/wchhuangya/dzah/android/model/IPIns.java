package com.ch.wchhuangya.dzah.android.model;

import java.io.Serializable;

/**
 * 淘宝IP地址库返回JSON所对应的Model
 * Created by wchya on 16/8/11.
 */
public class IPIns implements Serializable {
    private String ip;
    private String country;
    private String area;
    private String region;
    private String city;
    private String county;
    private String isp;

    /** IP地址 */
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /** 国家 */
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /** 省 */
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    /** 自治区 */
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    /** 市 */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /** 县 */
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    /** 运营商 */
    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
