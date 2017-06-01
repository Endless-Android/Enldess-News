package com.endless.enldess_news.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/5/16.
 */

public class OkhttpweatherUtil {

    public static void senOkHttpRequest(String adress, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(adress).build();
        client.newCall(request).enqueue(callback);
    }
}
