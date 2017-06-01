package com.endless.enldess_news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.endless.enldess_news.R;
import com.endless.enldess_news.bean.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by Administrator on 2017/5/20.
 */

public class LoginBmobActivity extends AppCompatActivity {


    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.llayout)
    LinearLayout mLlayout;
    @BindView(R.id.register)
    TextView mRegister;
    @BindView(R.id.activity_login)
    RelativeLayout mActivityLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bmob);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String userName = mTilUsername.getEditText().getText().toString().trim();
                String passWord = mTilPassword.getEditText().getText().toString().trim();
                BmobUser.loginByAccount(userName, passWord, new LogInListener<UserBean>() {

                    @Override
                    public void done(UserBean userBean, BmobException e) {
                        if (userBean != null) {
                            Toast.makeText(LoginBmobActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginBmobActivity.this, MyMainActivity.class));
                            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                            finish();

                        } else {
                            Toast.makeText(LoginBmobActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                break;

            case R.id.register:
                Log.e("aaaa", "onClick: ");
                startActivity(new Intent(LoginBmobActivity.this, RegisterActivity.class));
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
                break;
        }
    }
}
