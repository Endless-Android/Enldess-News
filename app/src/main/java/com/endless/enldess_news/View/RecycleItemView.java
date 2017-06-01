package com.endless.enldess_news.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.endless.enldess_news.R;
import com.endless.enldess_news.gson.NewsBean;
import com.endless.enldess_news.utils.MyBitmapUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/16.
 */

public class RecycleItemView extends RelativeLayout {


    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public RecycleItemView(Context context) {
        super(context);
        init();
    }

    public RecycleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item, this);
        ButterKnife.bind(this, this);

    }

    public void bindView(NewsBean.ResultBean.DataBean s) {
        MyBitmapUtils myBitmapUtils = new MyBitmapUtils();
        String date = s.getDate();
        String title = s.getTitle();
        String thumbnail_pic_s = s.getThumbnail_pic_s();
        mTvTitle.setText(date);
        mTvContent.setText(title);
        myBitmapUtils.display(mImageView, thumbnail_pic_s);
    }
}
