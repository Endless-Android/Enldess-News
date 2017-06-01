package com.endless.enldess_news.utils;

import com.endless.enldess_news.bean.ChatMessage;
import com.endless.enldess_news.bean.TulinResultBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/26.
 */

public class TulinHttpUtils {
    private static final String URL = "http://www.tuling123.com/openapi/api";
    private static final String API_KEY = "eb62a9dd24e64860b64b37595e1ffe92";
    private static String sParams;
    //http://www.tuling123.com/openapi/api?key=eb62a9dd24e64860b64b37595e1ffe92&info=你好啊


    //发送消息得到消息

    public static ChatMessage sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage();
        String jsonRes = doGet(msg);
        Gson gson = new Gson();
        TulinResultBean bean = null;
        try {
            bean = gson.fromJson(jsonRes, TulinResultBean.class);
            chatMessage.setMsg(bean.getText());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            chatMessage.setMsg("服务器繁忙");
        }
        chatMessage.setDate(new Date());
        chatMessage.setType(1);
        return chatMessage;
    }

    private static String doGet(String msg) {
        String result = "";
        String url = setParams(msg);
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            java.net.URL conurl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) conurl.openConnection();
            connection.setConnectTimeout(5000);     //建立连接的超时时间；
            connection.setReadTimeout(5000);        //传递数据的超时时间。
            connection.setRequestMethod("GET");
            is = connection.getInputStream();
            int len = -1;
            byte[] bytes = new byte[1024];
            baos = new ByteArrayOutputStream();
            while ((len = is.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            baos.flush();
            result = new String(baos.toByteArray());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static String setParams(String params) {
        String url = "";
        try {
            url = URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return url;
    }
}
