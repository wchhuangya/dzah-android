package com.ch.wchhuangya.dzah.android.model;

/**
 * GitHub 贡献者
 * Created by wchya on 16/11/1.
 */

public class Contributor implements BaseModel {
    String login;
    int contributions;

    public String getLogin() {
        return login;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
