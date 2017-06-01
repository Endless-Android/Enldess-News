package com.endless.enldess_news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.endless.enldess_news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/15.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

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
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.title_text)
    TextView mTitleText;
    private LinearLayout[] mLinearLayouts;
    private Fragment[] mFragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        mToolbar.setTitle("");
        mTitleText.setText("阅读");
        setSupportActionBar(mToolbar);
        int type = extras.getInt("type", -1);
        mFragments = new Fragment[4];
        mBottomNewLayout.setOnClickListener(this);
        mBottomReadLayout.setOnClickListener(this);
        mBottomVideoLayout.setOnClickListener(this);
        mBottomLampLayout.setOnClickListener(this);
        mBottomPcLayout.setOnClickListener(this);
        mLinearLayouts = new LinearLayout[4];
        mLinearLayouts[0] = mBottomReadLayout;
        mLinearLayouts[1] = mBottomVideoLayout;
        mLinearLayouts[2] = mBottomLampLayout;
        mLinearLayouts[3] = mBottomPcLayout;
        mLinearLayouts[type].setSelected(true);

        fragmentManager = getSupportFragmentManager();
        //获取fragment实例
        mFragments[0] = fragmentManager.findFragmentById(R.id.fragement_read);
        mFragments[1] = fragmentManager.findFragmentById(R.id.fragement_video);
        mFragments[2] = fragmentManager.findFragmentById(R.id.fragement_lamp);
        mFragments[3] = fragmentManager.findFragmentById(R.id.fragement_pc);
        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);//通过hide显示隐藏当前fragment
        fragmentTransaction.show(mFragments[type]).commit();

    }


    @Override
    public void onClick(View v) {
        mBottomReadLayout.setSelected(false);
        mBottomVideoLayout.setSelected(false);
        mBottomLampLayout.setSelected(false);
        mBottomPcLayout.setSelected(false);
        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);//通过hide显示隐藏当前fragment
        switch (v.getId()) {
            case R.id.bottom_new_layout:
                startActivity(new Intent(BaseActivity.this, MyMainActivity.class));

                break;

            case R.id.bottom_read_layout:
                mBottomReadLayout.setSelected(true);
                fragmentTransaction.show(mFragments[0]).commit();
                break;

            case R.id.bottom_video_layout:
                mBottomVideoLayout.setSelected(true);
                fragmentTransaction.show(mFragments[1]).commit();
                break;

            case R.id.bottom_lamp_layout:
                mBottomLampLayout.setSelected(true);
                fragmentTransaction.show(mFragments[2]).commit();
                break;

            case R.id.bottom_pc_layout:
                mBottomPcLayout.setSelected(true);
                fragmentTransaction.show(mFragments[3]).commit();
                break;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(getApplication());
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }


}
