package com.endless.enldess_news.application;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/5/18.
 */

public class BaseApplication extends Application {


    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(getApplicationContext(), "5acda60a3fe0a73d9a5e62b7499435ac");
        instance = this;
        initTbs();
    }

    private void initTbs() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                Log.i("aa", "onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.i("aa", "onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                Log.i("aa", "onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.i("aa", "onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
