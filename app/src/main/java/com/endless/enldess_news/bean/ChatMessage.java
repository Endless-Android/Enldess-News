package com.endless.enldess_news.bean;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/26.
 */

public class ChatMessage {

    private String name;
    private String msg;
    private int type;
    private Date date;


    public ChatMessage(){}

    public ChatMessage(String msg, int type, Date date) {
        this.msg = msg;
        this.type = type;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
