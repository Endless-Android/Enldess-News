package com.endless.enldess_news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.endless.enldess_news.R;
import com.endless.enldess_news.ui.fragment.NewFragment;
import com.endless.enldess_news.ui.fragment.PcFragment;
import com.endless.enldess_news.ui.fragment.ReadFragment;
import com.endless.enldess_news.ui.fragment.VideoFragment;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.fragment;

/**
 * Created by Administrator on 2017/5/21.
 */

public class MyMainActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ca_title_text)
    TextView mCaTitleText;
    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.news_image)
    ImageButton mNewsImage;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.bottom_new_layout)
    LinearLayout mBottomNewLayout;
    @BindView(R.id.read_image)
    ImageButton mReadImage;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.bottom_read_layout)
    LinearLayout mBottomReadLayout;
    @BindView(R.id.video_image)
    ImageButton mVideoImage;
    @BindView(R.id.bottom_video_layout)
    LinearLayout mBottomVideoLayout;
    @BindView(R.id.lamp_image)
    ImageButton mLampImage;
    @BindView(R.id.textView3)
    TextView mTextView3;
    @BindView(R.id.bottom_lamp_layout)
    LinearLayout mBottomLampLayout;
    @BindView(R.id.pc_image)
    ImageButton mPcImage;
    @BindView(R.id.bottom_pc_layout)
    LinearLayout mBottomPcLayout;
    @BindView(R.id.main_content)
    CoordinatorLayout mMainContent;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    private Gson mGson;
    private NewFragment mNewsFragment;
    private ReadFragment mReadFragment;
    private VideoFragment mVideoFragment;
    private PcFragment mPcFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private String mFragment_type;
    private int theme = R.style.AppTheme;
    private static boolean isExit = false;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymain);
        ButterKnife.bind(this);
        mToolbar.setTitle("");
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mCaTitleText.setText("新闻");
        Intent intent = this.getIntent();
        mFragment_type = intent.getStringExtra("fragment");
        Log.e("7777777777777", "onCreate: " + fragment);
        if (mFragment_type != null) {
            initFragmentType();
        } else {
            initFragment();
        }
        initlisten();
        init();
        View headerView = mNavigationView.getHeaderView(0);
        ImageView QR_code = (ImageView) headerView.findViewById(R.id.QR_code);
        QR_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyMainActivity.this,QRcodeActivity.class));
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


    private void initFragmentType() {
        mBottomReadLayout.setSelected(false);
        mBottomVideoLayout.setSelected(false);
        mBottomLampLayout.setSelected(false);
        mBottomPcLayout.setSelected(false);
        mBottomNewLayout.setSelected(false);
        switch (mFragment_type) {
            case "news":
                initFragment();
                mCaTitleText.setText("新闻");
                //setTheme();
                break;

            case "read":
                if (mReadFragment == null) {
                    mReadFragment = new ReadFragment();
                }
                mTransaction.replace(R.id.content, mReadFragment);
                mBottomReadLayout.setSelected(true);
                mTransaction.commit();
                break;

            case "video":
                if (mVideoFragment == null) {
                    mVideoFragment = new VideoFragment();
                }
                mBottomVideoLayout.setSelected(true);
                mCaTitleText.setText("聊天机器人");
                mTransaction.replace(R.id.content, mVideoFragment);
                mTransaction.commit();
                break;

            case "pc":
                if (mPcFragment == null) {
                    mPcFragment = new PcFragment();
                }
                mBottomPcLayout.setSelected(true);
                mCaTitleText.setText("我");
                mTransaction.replace(R.id.content, mPcFragment);
                mTransaction.commit();
                break;


        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }

    private void initFragment() {
        if (mNewsFragment == null) {
            mNewsFragment = new NewFragment();
        }
        mTransaction.replace(R.id.content, mNewsFragment);
        mBottomNewLayout.setSelected(true);
        //提交事务
        mTransaction.commit();
    }

    private void initlisten() {
        mBottomNewLayout.setOnClickListener(this);
        mBottomReadLayout.setOnClickListener(this);
        mBottomVideoLayout.setOnClickListener(this);
        mBottomLampLayout.setOnClickListener(this);
        mBottomPcLayout.setOnClickListener(this);
    }

    private void init() {
        mGson = new Gson();
        //setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerlayout, mToolbar, 0, 0);
        mDrawerlayout.setDrawerListener(toggle);
        toggle.syncState();

    }


    @Override
    public void onClick(View v) {
        mBottomReadLayout.setSelected(false);
        mBottomVideoLayout.setSelected(false);
        mBottomLampLayout.setSelected(false);
        mBottomPcLayout.setSelected(false);
        mBottomNewLayout.setSelected(false);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.bottom_new_layout:
                mCaTitleText.setText("新闻");
                mBottomNewLayout.setSelected(true);
                fragmentTransaction.replace(R.id.content, new NewFragment());
                break;

            case R.id.bottom_read_layout:
                mBottomReadLayout.setSelected(true);
                mCaTitleText.setText("聊天机器人");
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.hold, R.anim.fade);
                //fragmentTransaction.replace(R.id.content, new ReadFragment());
                break;

            case R.id.bottom_video_layout:
                mBottomVideoLayout.setSelected(true);
                fragmentTransaction.replace(R.id.content, new VideoFragment());
                break;

            case R.id.bottom_lamp_layout:
                mBottomLampLayout.setSelected(true);
                Intent intent = new Intent(this, WeatherActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;

            case R.id.bottom_pc_layout:
                mBottomPcLayout.setSelected(true);
                mCaTitleText.setText("我");
                fragmentTransaction.replace(R.id.content, new PcFragment());
                break;


        }
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        if (mDrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
