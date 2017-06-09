package com.endless.enldess_news.ui.activity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.endless.enldess_news.R;
import com.endless.enldess_news.View.MyVideoView;
import com.endless.enldess_news.bean.UserBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/5/23.
 */

public class RegisterActivity extends AppCompatActivity {
    private static final int CAMERA_SUCCESS = 1001;
    private static final int PHOTO_SUCCESS = 1002;
    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.phoneNum)
    EditText mPhoneNum;
    @BindView(R.id.til_phoneNum)
    TextInputLayout mTilPhoneNum;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.pagain)
    EditText mPagain;
    @BindView(R.id.til_again)
    TextInputLayout mTilAgain;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.llayout)
    LinearLayout mLlayout;
    @BindView(R.id.activity_login)
    RelativeLayout mActivityLogin;
    @BindView(R.id.head_portrait)
    ImageView mHeadPortrait;
    @BindView(R.id.videoView)
    MyVideoView mVideoView;
    @BindView(R.id.username)
    EditText mUsername;
    private Bitmap mBitmap;
    private FileOutputStream mOut;
    private File mDirFile;
    private String mVideoPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mVideoPath = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.background_gif).toString();
        initVideo();

    }

    private void initVideo() {

        mVideoView.setVideoPath(mVideoPath);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);    ////设置是否对播放的东东进行循环播放。

            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.setVideoPath(mVideoPath);
                mVideoView.start();
            }
        });
    }


    @OnClick({R.id.head_portrait, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_portrait:
                final CharSequence[] item = {"手机相册", "相机拍摄"};
                new AlertDialog.Builder(RegisterActivity.this).setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 1) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_SUCCESS);
                        } else {
                            Intent intent_photo = new Intent(Intent.ACTION_GET_CONTENT);
                            intent_photo.setType("image/*");
                            startActivityForResult(intent_photo, PHOTO_SUCCESS);
                        }
                    }
                }).create().show();


                break;
            case R.id.login:
                UserBean bean = new UserBean();
                String username = mUsername.getText().toString().trim();
                String phone = mPhoneNum.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String passagin = mPagain.getText().toString().trim();
                //String head = mDirFile.getAbsolutePath().toString();
                if (password.equals(passagin)) {
                   /* if (head != null) {
                        bean.setHeader(head);
                    } else {
                        bean.setHeader(null);
                    }*/
                    bean.setMobilePhoneNumber(phone);
                    bean.setPassword(passagin);
                    bean.setUsername(username);
                    bean.signUp(new SaveListener<UserBean>() {
                        @Override
                        public void done(UserBean userBean, BmobException e) {
                            if (e == null) {
                                Toast.makeText(RegisterActivity.this, "注册成功:", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.i("aaaa", "done: " + e.toString());
                            }

                        }
                    });
                }


                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ContentResolver resolver = getContentResolver();
        if (resultCode == CAMERA_SUCCESS) {
            Bundle extras = data.getExtras();
            mBitmap = (Bitmap) extras.get("data");
            if (mBitmap != null) {
                Log.i("aaaaaaa", "onActivityResult: ");
                mHeadPortrait.setImageBitmap(mBitmap);
                saveBitmap(mBitmap);
            }
        }
        if (resultCode == PHOTO_SUCCESS) {
            Uri data1 = data.getData();
            try {
                mBitmap = BitmapFactory.decodeStream(resolver.openInputStream(data1));
                if (mBitmap != null) {
                    Log.i("aaaaaaa", "onActivityResult: ");
                    mHeadPortrait.setImageBitmap(mBitmap);
                    saveBitmap(mBitmap);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void saveBitmap(Bitmap bitmap) {
        String mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Image/";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) // 判断是否可以对SDcard进行操作
        {    // 获取SDCard指定目录下

            //目录转化成文件夹
            mDirFile = new File(mFilePath + System.currentTimeMillis() + ".jpg");
            mDirFile.getParentFile().mkdirs();
            try {
                mOut = new FileOutputStream(mDirFile.getAbsoluteFile());
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, mOut);
                Log.i("bitmap", "saveBitmap: " + mDirFile.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                mOut.flush();
                mOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(RegisterActivity.this, "保存已经至" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/Image/" + "目录文件夹下", Toast.LENGTH_SHORT).show();
        }

    }
}
