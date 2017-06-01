package com.endless.enldess_news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.endless.enldess_news.View.RecycleItemView;
import com.endless.enldess_news.gson.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    private Context mContext;
    private OnItemClickListen mOnItemClickListener = null;
    private List<NewsBean.ResultBean.DataBean> mData = new ArrayList<NewsBean.ResultBean.DataBean>();


    public MyRecycleViewAdapter(Context context, List<NewsBean.ResultBean.DataBean> content) {
        mData = content;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new RecycleItemView(mContext));
    }

    @Override
    public void onBindViewHolder(final MyRecycleViewAdapter.ViewHolder holder, final int position) {
        holder.mRecycleItemView.bindView(mData.get(position));
        holder.mRecycleItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClick(holder.mRecycleItemView, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecycleItemView mRecycleItemView;

        public ViewHolder(RecycleItemView itemView) {
            super(itemView);
            mRecycleItemView = itemView;

        }
    }

    public interface OnItemClickListen {
        void OnItemClick(View v, int position);
    }


    public void setOnItemClickListener(OnItemClickListen listener) {
        this.mOnItemClickListener = listener;
    }
}
