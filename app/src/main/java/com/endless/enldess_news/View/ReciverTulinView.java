package com.endless.enldess_news.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.endless.enldess_news.R;
import com.endless.enldess_news.bean.ChatMessage;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/27.
 */

public class ReciverTulinView extends RelativeLayout {
    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.receive_message)
    TextView mReceiveMessage;

    public ReciverTulinView(Context context) {
        this(context, null);
    }

    public ReciverTulinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.receivemsg, this);
        ButterKnife.bind(this,this);

    }

    public void bindView(ChatMessage chatMessage) {
        Log.e("bindViewbew", "bindView: " + chatMessage.getMsg() + "--" + chatMessage.getType());
        mReceiveMessage.setText(chatMessage.getMsg());
        SimpleDateFormat time = new SimpleDateFormat("yyyy MM dd HH mm ss");
        mTimestamp.setText(time.format(chatMessage.getDate()));
    }
}
