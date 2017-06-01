package com.endless.enldess_news.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */

public class ViewPagerAdatper extends PagerAdapter {
    private List<View> mViewList;
    private List<View> mBitmaps;
    private boolean isHead = false;
    private List<ImageView> imagesDots = new ArrayList<ImageView>();//展示图片位置的小圆点
    private int delayTime; //轮播图延迟换图的时间


    public ViewPagerAdatper() {

    }

    public ViewPagerAdatper(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    public ViewPagerAdatper(List<View> mViewList, boolean isHead) {
        this.mBitmaps = mViewList;
        this.isHead = isHead;
    }



    @Override
    public int getCount() {
        if (isHead) {
            return mBitmaps.size();
        } else {
            return mViewList.size();
        }

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (isHead) {
            container.addView(mBitmaps.get(position % mBitmaps.size()));
            return mBitmaps.get(position);
        } else {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (isHead) {
            container.removeView(mBitmaps.get(position));
        } else {
            container.removeView(mViewList.get(position));
        }
    }
}
