package com.endless.enldess_news.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.endless.enldess_news.R;
import com.endless.enldess_news.bean.UserBean;
import com.endless.enldess_news.ui.activity.CollectActivity;
import com.endless.enldess_news.ui.activity.LoginBmobActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

public class PcFragment extends BaseFragment {

    @BindView(R.id.imageView2)
    ImageView mImageView2;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.pc_login)
    TextView mPcLogin;
    @BindView(R.id.mycollect)
    LinearLayout mMycollect;
    @BindView(R.id.pc_out)
    TextView mPcOut;
    Unbinder unbinder;
    @BindView(R.id.night_theme)
    TextView mNightTheme;
    Unbinder unbinder1;


    @Override
    protected void init() {


        UserBean bean = BmobUser.getCurrentUser(UserBean.class);
        if (bean != null) {
            String username = bean.getUsername();
            mPcLogin.setText("欢迎你：" + username);
            mPcOut.setVisibility(View.VISIBLE);
            mProfileImage.setImageResource(R.mipmap.eddies);

        }


        mPcLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginBmobActivity.class));

            }
        });

        mMycollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CollectActivity.class));
            }
        });

        mNightTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public int getLayoutID() {
        return R.layout.pc_fragment_main_layout;
    }


    @OnClick(R.id.pc_out)
    public void onViewClicked() {
        BmobUser.logOut();
        mPcOut.setVisibility(View.GONE);
        mPcLogin.setText("点击登录");


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
