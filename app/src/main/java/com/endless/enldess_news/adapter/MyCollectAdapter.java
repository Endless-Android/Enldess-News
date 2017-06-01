package com.endless.enldess_news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.endless.enldess_news.View.MyCollectView;
import com.endless.enldess_news.bean.CollectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.MyViewHolder> {

    private Context mContext;
    private List<CollectBean> mList = new ArrayList<CollectBean>();


    public MyCollectAdapter(Context context, List<CollectBean> collectBeen) {
        mContext = context;
        mList = collectBeen;
    }

    @Override
    public MyCollectAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(new MyCollectView(mContext));
    }

    @Override
    public void onBindViewHolder(MyCollectAdapter.MyViewHolder holder, int position) {
        holder.mMyCollectView.bindView(mList.get(position));


    }


    @Override
    public int getItemCount() {

        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyCollectView mMyCollectView;


        public MyViewHolder(MyCollectView itemView) {
            super(itemView);
            mMyCollectView = itemView;
        }
    }
}