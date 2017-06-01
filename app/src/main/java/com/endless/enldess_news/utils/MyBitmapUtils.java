package com.endless.enldess_news.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.endless.enldess_news.R;

/**
 * Created by Administrator on 2017/5/23.
 */

public class MyBitmapUtils {

    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public MyBitmapUtils() {
        mMemoryCacheUtils = new MemoryCacheUtils();
        mLocalCacheUtils = new LocalCacheUtils();
        mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils, mMemoryCacheUtils);
    }

    public void display(ImageView iv, String url) {
        iv.setImageResource(R.mipmap.loading);  //默认图片
        Bitmap bitmap;
        bitmap = mMemoryCacheUtils.getBitmap(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
            return;
        }
        bitmap = mLocalCacheUtils.getBitmap(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
            mMemoryCacheUtils.setBitmap(url, bitmap);
            return;
        }

        mNetCacheUtils.getBitmap(iv, url);

    }

    public Bitmap getImag(ImageView iv, String url) {

        iv.setImageResource(R.mipmap.ic_launcher);  //默认图片
        Bitmap bitmap;
        bitmap = mMemoryCacheUtils.getBitmap(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
            return bitmap;
        }
        bitmap = mLocalCacheUtils.getBitmap(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
            mMemoryCacheUtils.setBitmap(url, bitmap);
            return bitmap;
        }

        return mNetCacheUtils.getImag(iv, url);

    }

}
