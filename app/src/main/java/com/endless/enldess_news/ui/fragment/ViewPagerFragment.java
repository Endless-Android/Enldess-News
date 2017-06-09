package com.endless.enldess_news.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.endless.enldess_news.R;
import com.endless.enldess_news.View.CustomTransformer;
import com.endless.enldess_news.adapter.MyRecycleViewAdapter;
import com.endless.enldess_news.adapter.ViewPagerAdatper;
import com.endless.enldess_news.gson.NewsBean;
import com.endless.enldess_news.ui.activity.NewsContentActivity;
import com.endless.enldess_news.utils.MyBitmapUtils;
import com.endless.enldess_news.utils.OkhttpweatherUtil;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/16.
 */

public class ViewPagerFragment extends Fragment {

    @BindView(R.id.mt_recycleView)
    XRecyclerView mMtRecycleView;
    private int mColor;
    private int mTitle;
    private String[] mContenr;
    private int x;
    private MyRecycleViewAdapter mAdapter;
    private Gson mGson;
    private List<NewsBean.ResultBean.DataBean> mData = new ArrayList<NewsBean.ResultBean.DataBean>();
    Handler m = new Handler();
    private String adresss;
    private MyBitmapUtils mMyBitmapUtils;
    private List<View> mBitmaps = new ArrayList<View>();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mAdapter.notifyDataSetChanged();
            mMtRecycleView.refreshComplete();
            initImag();

        }
    };
    private ViewPagerAdatper mAdapter1;
    private ImageView mImageViews_02;
    private ImageView mImageView_01, mImageViews_03;
    private String mThumbnail_pic_s02;
    private String mThumbnail_pic_s03;
    private ViewPager mHeader;
    Unbinder unbinder;
    private View mV;
    private ImageView mOneDots;
    private ImageView mTwoDots;
    private ImageView mThreeDots;
    private String mThumbnail_pic_s01;

    public static ViewPagerFragment newInstance(int title, int color, NewsBean bean) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("title", title);
        bundle.putInt("color", color);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView() {

        mAdapter = new MyRecycleViewAdapter(getContext(), mData);
        mAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListen() {
            @Override
            public void OnItemClick(View v, int position) {
                Intent intent = new Intent(getContext(), NewsContentActivity.class);
                intent.putExtra("url", mData.get(position).getUrl());
                intent.putExtra("title", mData.get(position).getTitle());
                intent.putExtra("imag", mData.get(position).getThumbnail_pic_s());
                startActivity(intent);

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMtRecycleView.setLayoutManager(layoutManager);
        mMtRecycleView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mV = LayoutInflater.from(getContext()).inflate(R.layout.header_viewpager, null, false);
        mOneDots = (ImageView) mV.findViewById(R.id.one_dots);
        mTwoDots = (ImageView) mV.findViewById(R.id.two_dots);
        mThreeDots = (ImageView) mV.findViewById(R.id.three_dots);
        mHeader = (ViewPager) mV.findViewById(R.id.head_image);
        mHeader.setPageTransformer(true, new CustomTransformer());
        mMyBitmapUtils = new MyBitmapUtils();
        //initImag();
        mMtRecycleView.addHeaderView(mV);

        mMtRecycleView.setHasFixedSize(true);
        mMtRecycleView.setAdapter(mAdapter);
        mMtRecycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreash();
            }

            @Override
            public void onLoadMore() {

            }
        });

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getArguments().getInt("title");
        mColor = getArguments().getInt("color");
        mContenr = getArguments().getStringArray("msgs");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        mImageView_01 = new ImageView(getContext());
        mImageView_01.setScaleType(ImageView.ScaleType.FIT_XY);
        mImageViews_02 = new ImageView(getContext());
        mImageViews_02 = new ImageView(getContext());
        mImageViews_02.setScaleType(ImageView.ScaleType.FIT_XY);
        mImageViews_03 = new ImageView(getContext());
        mImageViews_03.setScaleType(ImageView.ScaleType.FIT_XY);
        initDate();
        initView();
        addDots();
        moveDots();
        Log.i("ssssssss", "onCreateView: " + mData.size());
        mAdapter.notifyDataSetChanged();
        return view;


    }


    private void addDots() {
        mOneDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHeader.setCurrentItem(0);
            }
        });

        mTwoDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHeader.setCurrentItem(1);
            }
        });

        mThreeDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHeader.setCurrentItem(2);
            }
        });

    }

    private void moveDots() {
        mHeader.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mOneDots.setImageResource(R.drawable.light_dot);
                    mTwoDots.setImageResource(R.drawable.gray_dot);
                    mThreeDots.setImageResource(R.drawable.gray_dot);
                } else if (position == 1) {
                    mOneDots.setImageResource(R.drawable.gray_dot);
                    mTwoDots.setImageResource(R.drawable.light_dot);
                    mThreeDots.setImageResource(R.drawable.gray_dot);
                } else if (position == 2) {
                    mOneDots.setImageResource(R.drawable.gray_dot);
                    mTwoDots.setImageResource(R.drawable.gray_dot);
                    mThreeDots.setImageResource(R.drawable.light_dot);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

    }


    private void refreash() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m.post(mRunnable);

            }
        }).start();
    }


    private void initImag() {
        mAdapter1 = new ViewPagerAdatper(mBitmaps, true);
        mMyBitmapUtils.display(mImageView_01, mThumbnail_pic_s02);
        mMyBitmapUtils.display(mImageViews_02, mThumbnail_pic_s03);
        mMyBitmapUtils.display(mImageViews_03, mThumbnail_pic_s01);
        mBitmaps.add(mImageView_01);
        mBitmaps.add(mImageViews_02);
        mBitmaps.add(mImageViews_03);
        mOneDots.setVisibility(View.VISIBLE);
        mTwoDots.setVisibility(View.VISIBLE);
        mThreeDots.setVisibility(View.VISIBLE);
        mHeader.setAdapter(mAdapter1);
        mHeader.setCurrentItem(1);
    }

    private void initDate() {
        mGson = new Gson();
        switch (mTitle) {
            case 0:
                adresss = "http://v.juhe.cn/toutiao/index?type=top&key=ad93d1b5e4ec698c2e3d8f344503c3e5";
                break;
            case 1:
                adresss = "http://v.juhe.cn/toutiao/index?type=shehui&key=ad93d1b5e4ec698c2e3d8f344503c3e5";
                break;
            case 2:
                adresss = "http://v.juhe.cn/toutiao/index?type=guonei&key=ad93d1b5e4ec698c2e3d8f344503c3e5";
                break;
            case 3:
                adresss = "http://v.juhe.cn/toutiao/index?type=yule&key=ad93d1b5e4ec698c2e3d8f344503c3e5";
                break;
            case 4:
                adresss = "http://v.juhe.cn/toutiao/index?type=tiyu&key=ad93d1b5e4ec698c2e3d8f344503c3e5";
                break;
            case 5:
                adresss = "http://v.juhe.cn/toutiao/index?type=junshi&key=ad93d1b5e4ec698c2e3d8f344503c3e5";
                break;
            case 6:
                adresss = "http://v.juhe.cn/toutiao/index?type=keji&key=ad93d1b5e4ec698c2e3d8f344503c3e5";
                break;
            case 7:
                adresss = "http://v.juhe.cn/toutiao/index?type=caijing&key=ad93d1b5e4ec698c2e3d8f344503c3e5";
                break;
            case 8:
                adresss = "http://v.juhe.cn/toutiao/index?type=shishang&key=ad93d1b5e4ec698c2e3d8f344503c3e5";
                break;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkhttpweatherUtil.senOkHttpRequest(adresss, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("aaaaaa", "onResponse: 数据加载失败" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String date = response.body().string();
                        try {
                            JSONObject object = new JSONObject(date);
                            NewsBean bean = mGson.fromJson(object.toString(), NewsBean.class);
                            List<NewsBean.ResultBean.DataBean> Data = bean.getResult().getData();
                            mThumbnail_pic_s02 = Data.get(0).getThumbnail_pic_s02();
                            mThumbnail_pic_s03 = Data.get(0).getThumbnail_pic_s03();
                            mThumbnail_pic_s01 = Data.get(0).getThumbnail_pic_s();
                            mData.addAll(Data);
                            m.post(mRunnable);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
