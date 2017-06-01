package com.endless.enldess_news.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/5/23.
 */

public class UserBean extends BmobUser {
    private String header;
    private String phoneNumber;
    private String userName;
    private String passWord;
    private String titleUrl;
    private String imagUrl;
    private boolean iscoll;
    private String contentUrl;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }


    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }


    public boolean iscoll() {
        return iscoll;
    }

    public void setIscoll(boolean iscoll) {
        this.iscoll = iscoll;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


}
