package com.endless.enldess_news.bean;

/**
 * Created by Administrator on 2017/5/26.
 */

public class TulinResultBean {


    /**
     * code : 100000
     * text : 嘿嘿，你好我好，大家都好～
     */

    private int code;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TulinResultBean{" +
                "code=" + code +
                ", text='" + text + '\'' +
                '}';
    }
}
