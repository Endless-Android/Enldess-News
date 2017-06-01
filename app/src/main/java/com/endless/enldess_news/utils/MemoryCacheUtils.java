package com.endless.enldess_news.utils;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/5/23.
 */

public class MemoryCacheUtils {


    private HashMap<String, SoftReference<Bitmap>> mMemoryCache;


    public MemoryCacheUtils() {
       // long maxMemory = Runtime.getRuntime().maxMemory() / 8;//得到手机最大允许内存的1/8,即超过指定内存,则开始回收
        mMemoryCache = new HashMap<String, SoftReference<Bitmap>>();


    }

    public Bitmap getBitmap(String url) {
        SoftReference<Bitmap> bitmapSoftReference = mMemoryCache.get(url);
        if (bitmapSoftReference != null) {
            Bitmap bitmap = bitmapSoftReference.get();
            return bitmap;
        }
        return null;
    }

    public void setBitmap(String url, Bitmap bitmap) {
        mMemoryCache.put(url, new SoftReference<>(bitmap));
    }
}
