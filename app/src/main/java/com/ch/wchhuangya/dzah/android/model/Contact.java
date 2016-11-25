package com.ch.wchhuangya.dzah.android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wchya on 2016-11-23 21:27
 */

public class Contact {
    private String name;
    private String sex;

    public Contact(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private static int nums = 0;

    public static List<Contact> generateContacts(int number) {
        List<Contact> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(new Contact("随机人员：" + nums++, nums % 3 == 0 ? "女" : "男" ));
        }
        return list;
    }
}
