package com.endless.enldess_news.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/5/20.
 */

public class CollectBean extends BmobObject {

    private String title;
    private String time;
    private String url;
    private String id;
    private String imagrl;
    private String user;
    private boolean iscoll;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean iscoll() {
        return iscoll;
    }

    public void setIscoll(boolean iscoll) {
        this.iscoll = iscoll;
    }

    public String getImagrl() {
        return imagrl;
    }

    public void setImagrl(String imagrl) {
        this.imagrl = imagrl;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
