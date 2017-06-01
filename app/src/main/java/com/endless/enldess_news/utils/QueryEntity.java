package com.endless.enldess_news.utils;

/**
 * Created by Administrator on 2017/5/25.
 */

public class QueryEntity {

    public String targetSelector;
    public Object methodParms;
    public String methodName;

    public QueryEntity(String targetSelector, String methodName, String methodParms) {
        this.targetSelector = targetSelector;
        this.methodParms = methodParms;
        this.methodName = methodName;
    }
}
