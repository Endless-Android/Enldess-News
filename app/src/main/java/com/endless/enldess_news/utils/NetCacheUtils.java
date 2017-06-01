package com.endless.enldess_news.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2017/5/23.
 */

public class NetCacheUtils {

    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;
    private Bitmap mBitmap;


    public NetCacheUtils(LocalCacheUtils localCacheUtils, MemoryCacheUtils memoryCacheUtils) {
        mLocalCacheUtils = localCacheUtils;
        mMemoryCacheUtils = memoryCacheUtils;
    }


    //网络上获取图片
    public void getBitmap(ImageView iv, String url) {
        new BitmapTask().execute(iv, url);

    }

    public Bitmap getImag(ImageView iv, String url) {
        new BitmapTask().execute(iv, url);
        return mBitmap;
    }


    /**
     * AsyncTask就是对handler和线程池的封装
     * 第一个泛型:参数类型
     * 第二个泛型:更新进度的泛型
     * 第三个泛型:onPostExecute的返回结果
     */
    class BitmapTask extends AsyncTask<Object, Void, Bitmap> {

        private ImageView ivPic;
        private String url;

        @Override
        protected Bitmap doInBackground(Object[] params) {
            ivPic = (ImageView) params[0];
            url = (String) params[1];

            return downLoadBitmap(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                ivPic.setImageBitmap(bitmap);
                mBitmap = bitmap;
                mLocalCacheUtils.setBitmap(url, bitmap);
                //保存至内存中
                mMemoryCacheUtils.setBitmap(url, bitmap);

            }
            super.onPostExecute(bitmap);
        }

        private Bitmap downLoadBitmap(String url) {

            Bitmap bitmap = null;
            try {
                URL adress = new URL(url);
                URLConnection connection = adress.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }

}
