package com.endless.enldess_news.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.endless.enldess_news.gson.NewsBean;
import com.endless.enldess_news.ui.fragment.ViewPagerFragment;

/**
 * Created by Administrator on 2017/5/16.
 */

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private String[] tabtitleArray;
    private Context mContext;
    private int[] mColors = new int[]{android.R.color.holo_orange_dark, android.R.color.holo_green_dark
            , android.R.color.holo_blue_dark};
    private NewsBean mNewsBean;
    private int mtite;

    public ViewPagerFragmentAdapter(FragmentManager fm, Context context, String[] tabTitle) {
        super(fm);
        tabtitleArray = tabTitle;
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        mtite = position;
        return ViewPagerFragment.newInstance(mtite, mColors[position % mColors.length], mNewsBean);
    }

    @Override
    public int getCount() {
        return tabtitleArray.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       // mtite = position;
        return tabtitleArray[position % tabtitleArray.length];
    }
}
