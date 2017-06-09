package com.endless.enldess_news.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.endless.enldess_news.R;
import com.endless.enldess_news.bean.UserBean;
import com.endless.enldess_news.ui.activity.AboutActivity;
import com.endless.enldess_news.ui.activity.CollectActivity;
import com.endless.enldess_news.ui.activity.LoginBmobActivity;

import butterknife.BindView;
import cn.bmob.v3.BmobUser;

public class PcFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_user_icon)
    ImageView mIvUserIcon;
    @BindView(R.id.tv_focus_num)
    TextView mTvFocusNum;
    @BindView(R.id.ll_focus)
    LinearLayout mLlFocus;
    @BindView(R.id.tv_fans_num)
    TextView mTvFansNum;
    @BindView(R.id.ll_fans)
    LinearLayout mLlFans;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.mycollect)
    LinearLayout mMycollect;
    @BindView(R.id.ll_my_histories)
    LinearLayout mLlMyHistories;
    @BindView(R.id.ll_help_center)
    LinearLayout mLlHelpCenter;
    @BindView(R.id.ll_user_backup)
    LinearLayout mLlUserBackup;
    @BindView(R.id.ll_about)
    LinearLayout mLlAbout;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_logout)
    Button mBtnLogout;

    @Override
    protected void init() {


        UserBean bean = BmobUser.getCurrentUser(UserBean.class);

        if (bean != null) {
            String username = bean.getUsername();
            mTvUsername.setText(username);
            mBtnLogout.setVisibility(View.VISIBLE);
            mBtnLogin.setVisibility(View.GONE);
            mIvUserIcon.setVisibility(View.VISIBLE);
            mLlFocus.setVisibility(View.VISIBLE);
            mLlFans.setVisibility(View.VISIBLE);
            mTvUsername.setVisibility(View.VISIBLE);
            mTvTitle.setVisibility(View.GONE);
        } else {

            mIvUserIcon.setVisibility(View.GONE);
            mLlFocus.setVisibility(View.GONE);
            mLlFans.setVisibility(View.GONE);
            mTvUsername.setVisibility(View.GONE);
            mTvTitle.setVisibility(View.VISIBLE);
        }


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginBmobActivity.class));
            }
        });

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();
                mBtnLogout.setVisibility(View.GONE);
                mBtnLogin.setVisibility(View.VISIBLE);
                mIvUserIcon.setVisibility(View.GONE);
                mLlFocus.setVisibility(View.GONE);
                mLlFans.setVisibility(View.GONE);
                mTvUsername.setVisibility(View.GONE);
                mTvTitle.setVisibility(View.VISIBLE);
            }
        });


        mLlAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AboutActivity.class));
            }
        });

        mMycollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CollectActivity.class));
            }
        });



    }


    @Override
    public int getLayoutID() {
        return R.layout.pc_fragment_main_layouts;
    }


}
