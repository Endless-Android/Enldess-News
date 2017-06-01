package com.endless.enldess_news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.endless.enldess_news.View.ReciverTulinView;
import com.endless.enldess_news.View.SendTulinView;
import com.endless.enldess_news.bean.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class TulinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int SEND = 1;
    private final static int RECIVER = 0;
    private List<ChatMessage> data = new ArrayList<>();
    private Context mContext;


    public TulinAdapter(Context context, List<ChatMessage> list) {
        mContext = context;
        data = list;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = data.get(position);
        return message.getType() == SEND ? SEND : RECIVER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("aaaaaaaaaaaaaaaaa", "onCreateViewHolder: " + viewType);
        if (viewType == SEND) {
            return new SendViewHolder(new SendTulinView(mContext));
        } else {
            return new ReceiveViewHolder(new ReciverTulinView(mContext));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("aaaaaaaaaaaaaaaaa", "onCreateViewHolder: " + data.get(position).getMsg());
        if (holder instanceof SendViewHolder) {
            ((SendViewHolder) holder).mSendTulinView.bindView(data.get(position));
        } else {
            ((ReceiveViewHolder) holder).mReciverTulinView.bindView(data.get(position));
        }

    }


    public void addNewMsg(ChatMessage message) {
        data.add(message);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class SendViewHolder extends RecyclerView.ViewHolder {
        private SendTulinView mSendTulinView;

        public SendViewHolder(SendTulinView itemView) {
            super(itemView);
            mSendTulinView = itemView;
        }
    }

    public class ReceiveViewHolder extends RecyclerView.ViewHolder {
        private ReciverTulinView mReciverTulinView;

        public ReceiveViewHolder(ReciverTulinView itemView) {
            super(itemView);
            mReciverTulinView = itemView;
        }
    }


}
