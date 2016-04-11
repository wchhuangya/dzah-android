package com.ch.wchhuangya.dzah.android.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Json助手类，使用的Jackson-core包
 * Created by wchya on 2015-10-09.
 */
public class JsonHelper {

    public static String getContacts(InputStream is) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String res = mapper.readValue(is, String.class);
            LogHelper.e(JsonHelper.class, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
