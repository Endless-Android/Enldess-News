package com.endless.enldess_news.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/5/23.
 */

public class LocalCacheUtils {

    private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WerbNews";

    public Bitmap getBitmap(String url) {

        try {

            File file = new File(PATH, url);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void setBitmap(String url, Bitmap bitmap) {
        try {
            File file = new File(PATH, url);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
