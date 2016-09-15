package com.ch.wchhuangya.dzah.android.enums;

/**
 * Bmob Android SDK 错误枚举
 * Created by wchya on 16/9/13.
 */
public enum BmobSDKError {
    ERROR_9001("Application Id为空，请初始化", 9001);

    /** 错误码对应的解释 */
    private String text;
    /** 错误码 */
    private int code;

    BmobSDKError(String text, int code) {
        this.text = text;
        this.code = code;
    }

    public String getText(int code) {
        for (BmobSDKError error : BmobSDKError.values()) {
            if (error.getCode() == code)
                return text;
        }
        return null;
    }

    public int getCode() {
        return code;
    }
}
