package com.endless.enldess_news.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.endless.enldess_news.R;
import com.endless.enldess_news.bean.CollectBean;
import com.endless.enldess_news.utils.MyBitmapUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/21.
 */

public class MyCollectView extends RelativeLayout {


    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.coll_content)
    TextView mCollContent;
    @BindView(R.id.cerate)
    TextView mCerate;
    @BindView(R.id.cerate_time)
    TextView mCerateTime;
    @BindView(R.id.item_delete)
    TextView mItemDelete;
    @BindView(R.id.item_layout)
    LinearLayout mItemLayout;

    public MyCollectView(Context context) {

        this(context, null);

    }

    public MyCollectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.activity_collect_item, this);
        ButterKnife.bind(this, this);

    }

    public void bindView(CollectBean collectBean) {
        MyBitmapUtils MyBitmapUtils = new MyBitmapUtils();
        mCerateTime.setText(collectBean.getTime());
        MyBitmapUtils.display(mImageView, collectBean.getImagrl());
        Log.e("aaaaaa", "bindView: "+collectBean.getTitle());
        mCollContent.setText(collectBean.getTitle());


    }
}
