package com.endless.enldess_news.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */

public class ViewPagerAdatper extends PagerAdapter {
    private List<View> mViewList;
    private List<View> mBitmaps;
    private boolean isHead = false;

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
            return Integer.MAX_VALUE;
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
            position %= mBitmaps.size();
            if (position<0){
                position = mBitmaps.size()+position;
            }
            ImageView view = (ImageView) mBitmaps.get(position);
            ViewParent vp =view.getParent();
            if (vp!=null){
                ViewGroup parent = (ViewGroup)vp;
                parent.removeView(view);
            }
            container.addView(view);
            return view;
        } else {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (isHead) {
            //container.removeView(mBitmaps.get(position));
        } else {
            container.removeView(mViewList.get(position));
        }
    }




}
