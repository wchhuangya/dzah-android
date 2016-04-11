package com.ch.wchhuangya.dzah.android.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by wchya on 2015-10-09.
 */
public class HttpHelper {

    public static InputStream loadResource(String url) throws Exception {
        try {
            URL m;
            URLConnection c;
            InputStream i = null;
            m = new URL(url);
            c = m.openConnection();
            c.setConnectTimeout(10);
            c.setReadTimeout(10);
            if (c.getContent()== null) {
                return null;
            }else {
                i = c.getInputStream();
            }
            return i;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("网络连接错误，请检查您的网络！", e);
        }
    }
}
