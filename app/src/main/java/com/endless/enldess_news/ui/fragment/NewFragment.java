package com.endless.enldess_news.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.endless.enldess_news.R;
import com.endless.enldess_news.adapter.ViewPagerFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class NewFragment extends Fragment {
    @BindView(R.id.my_tblayout)
    TabLayout mMyTblayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    Unbinder unbinder;
    private String[] title = {"头条", "社会", "国内", "娱乐", "体育", "军事", "科技", "财经", "时尚"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment_main_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getFragmentManager(), getContext(), title);
        mViewPager.setAdapter(adapter);
        mMyTblayout.setupWithViewPager(mViewPager);
        mMyTblayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
