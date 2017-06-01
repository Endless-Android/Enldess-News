package com.endless.enldess_news.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.endless.enldess_news.R;
import com.endless.enldess_news.adapter.TulinAdapter;
import com.endless.enldess_news.bean.ChatMessage;
import com.endless.enldess_news.utils.TulinHttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VideoFragment extends Fragment {

    private final static int SEND = 1;
    private final static int RECIVER = 0;
    @BindView(R.id.recyclreview)
    RecyclerView mRecyclreview;
    @BindView(R.id.message)
    EditText mMessage;
    @BindView(R.id.send)
    Button mSend;
    Unbinder unbinder;

    private List<ChatMessage> data = new ArrayList<>();
    private TulinAdapter mAdapter;
    //private ChatMessageAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChatMessage fromChatMessage = (ChatMessage) msg.obj;
            data.add(fromChatMessage);
            mRecyclreview.scrollToPosition(data.size()-1);
            mAdapter.notifyDataSetChanged();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment_main_layout, container, false);


        unbinder = ButterKnife.bind(this, view);
        ChatMessage chatMessage = new ChatMessage("你好,交院Eddie为你服务", RECIVER, new Date());
        data.add(chatMessage);
        mAdapter = new TulinAdapter(view.getContext(), data);
        mRecyclreview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclreview.setHasFixedSize(true);
        mRecyclreview.setAdapter(mAdapter);
        sendMsg();
        return view;
    }




    private void sendMsg() {
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = mMessage.getText().toString().toString();
                final ChatMessage chatMessage = new ChatMessage();
                chatMessage.setType(SEND);
                chatMessage.setDate(new Date());
                chatMessage.setMsg(message);
                data.add(chatMessage);
                mAdapter.notifyDataSetChanged();
                mRecyclreview.scrollToPosition(data.size()-1);
                mMessage.setText("");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ChatMessage chatMessage1 = TulinHttpUtils.sendMessage(message);
                        chatMessage1.setType(0);
                        chatMessage1.setDate(new Date());
                        Message message = new Message();
                        message.obj = chatMessage1;
                        handler.sendMessage(message);
                    }
                }).start();

            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
