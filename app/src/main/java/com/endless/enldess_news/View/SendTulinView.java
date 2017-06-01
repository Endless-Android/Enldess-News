package com.endless.enldess_news.View;

import android.content.Context;
import android.util.AttributeSet;
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
 * Created by Administrator on 2017/5/26.
 */

public class SendTulinView extends RelativeLayout {

    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.send_message)
    TextView mSendMessage;

    public SendTulinView(Context context) {
        this(context, null);
    }

    public SendTulinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.sendmsg, this);
        ButterKnife.bind(this,this);
    }


    public void bindView(ChatMessage chatMessage) {
        mSendMessage.setText(chatMessage.getMsg());
        SimpleDateFormat time = new SimpleDateFormat("yyyy MM dd HH mm ss");
        mTimestamp.setText(time.format(chatMessage.getDate()));



    }
}
