package com.endless.enldess_news.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.endless.enldess_news.R;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/5/15.
 */

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.splash_image)
    ImageView mSplashImage;
    private AnimationSet mSet;
    private String mIsfirst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Bmob.initialize(getApplicationContext(), "5acda60a3fe0a73d9a5e62b7499435ac");
        initTbs();
        SharedPreferences my_prf = this.getSharedPreferences("my_prf", 0);
        mIsfirst = my_prf.getString("isfirst", " ");
        initAnim();

    }

    private void initTbs() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                Log.i("aa", "onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.i("aa", "onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                Log.i("aa", "onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.i("aa", "onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    private void initAnim() {
        // 透明度动画
        AlphaAnimation a1 = new AlphaAnimation(0, 1.0f);
        a1.setDuration(2000);
        // 旋转:float fromDegrees, float toDegrees, int pivotXType, float pivotXValue,
        //int pivotYType, float pivotYValue
        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);
        // 缩放动画float fromX, float toX, float fromY, float toY,
        //int pivotXType, float pivotXValue, int pivotYType, float pivotYValue
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(2000);
        // 集合动画
        mSet = new AnimationSet(true);
        mSet.addAnimation(a1);
        mSet.addAnimation(sa);
        mSet.addAnimation(ra);
        mSplashImage.startAnimation(mSet);
        mSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mIsfirst.equals("false")) {
                    startActivity(new Intent(SplashActivity.this, MyMainActivity.class));

                } else {
                    startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


}
