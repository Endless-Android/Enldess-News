package com.endless.enldess_news.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/19.
 */

public abstract class BaseFragment extends Fragment {
    Activity mActivity;
    AppCompatActivity mAppCompatActivity;
    Unbinder unbinder;

/*
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        setHasOptionsMenu(true);
        initToolbar(getToolBareId(), getTitle());

    }*/

    /*protected abstract int getTitle();

    public abstract int getToolBareId();

    public Toolbar initToolbar(int toolbarId, int title) {
        AppCompatActivity mAppCompatActivity = (AppCompatActivity) mActivity;
        Toolbar toolbar = (Toolbar) mAppCompatActivity.findViewById(toolbarId);
        toolbar.setTitle(title);
        mAppCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutID(), container, false);
        unbinder = ButterKnife.bind(this, view);

        init();
        return view;
    }

    protected abstract void init();

    public abstract int getLayoutID();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

/*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);

    }*/


}
