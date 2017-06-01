package com.endless.enldess_news.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.endless.enldess_news.R;
import com.endless.enldess_news.bean.CollectBean;
import com.endless.enldess_news.bean.UserBean;
import com.endless.enldess_news.widget.MyIUiListener;
import com.tencent.connect.common.Constants;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.tauth.Tencent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017/5/18.
 */

public class NewsContentActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ca_title_text)
    TextView mCaTitleText;
    private String url;
    private WebView tx_webView;
    private Bundle params;
    private MyIUiListener mMyIUiListener = new MyIUiListener();
    private String title;
    private String time;
    private Date mCurDate;
    private String mTime;
    private String iamgurl;
    private int mCurrentItem = 2;
    private int mCurrentChooseItem;
    private CollectBean mBean;
    private boolean iscreate = false;
    private String mObjectId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newscontent);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ShareSDK.initSDK(this, "1e1c18dd972f7");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCaTitleText.setVisibility(View.GONE);
        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        url = extras.getString("url");
        title = extras.getString("title");
        time = extras.getString("time");
        iamgurl = extras.getString("imag");
        Log.e("aaaaa", "onCreate: " + title);
        judge();
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tx_webView = (WebView) findViewById(R.id.tx_webView);
        initTbs(url);

    }

    private void judge() {
        BmobQuery<CollectBean> query = new BmobQuery<CollectBean>();
        query.addWhereEqualTo("url", url);
        query.findObjects(new FindListener<CollectBean>() {
            @Override
            public void done(List<CollectBean> object, BmobException e) {
                if (e == null) {
                    if (object.size() == 0) {
                        mBean = new CollectBean();
                    } else {
                        Log.e("bmob", "成功：dfgsssssssssssssssssss");
                        iscreate = true;
                        invalidateOptionsMenu();
                        mBean = object.get(0);
                    }
                } else {
                    mBean = new CollectBean();
                    Log.e("bmob", "失败：" + e.getMessage() + ",-------" + e.getErrorCode());
                }
            }
        });

    }


    private void initTbs(String s) {
        tx_webView.getSettings().setJavaScriptEnabled(true);
        tx_webView.getSettings().setUseWideViewPort(true);
        tx_webView.loadUrl(s);
        tx_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                Log.i("aaaaa", "onReceivedError: 加载失败了");
            }
        });
        tx_webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView webView, int i) {
                if (i == 100) {
                    Log.i("aaaa", "onProgressChanged: 成功");
                }
            }
        });

    }

    private void initTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        //获取当前时间
        mCurDate = new Date(System.currentTimeMillis());
        mTime = formatter.format(mCurDate);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (tx_webView != null && tx_webView.canGoBack()) {
                tx_webView.goBack();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tx_webView != null) {
            tx_webView.destroy();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(getApplication());
        menuInflater.inflate(R.menu.main, menu);
        if (iscreate) {
            menu.findItem(R.id.coll).setIcon(R.mipmap.fav_press);
            menu.findItem(R.id.coll).setTitle("取消收藏");
        }


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (tx_webView != null && tx_webView.canGoBack()) {
                    tx_webView.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.share:
                showShare();

                break;

            case R.id.coll:

                UserBean userBean = BmobUser.getCurrentUser(UserBean.class);
                if (userBean != null) {
                    if (item.getTitle().equals("收藏")) {
                        initTime();
                        mBean.setTitle(title);
                        mBean.setTime(mTime);
                        mBean.setImagrl(iamgurl);
                        mBean.setUrl(url);
                        mBean.setIscoll(true);
                        if (iscreate) {
                            mBean.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        item.setIcon(R.mipmap.fav_press);
                                        Toast.makeText(NewsContentActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                        item.setTitle("取消收藏");
                                    }
                                }
                            });
                        } else {
                            mBean.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {

                                    if (e == null) {
                                        item.setIcon(R.mipmap.fav_press);
                                        Toast.makeText(NewsContentActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                        item.setTitle("取消收藏");
                                    }
                                }
                            });
                        }
                    } else {
                        mObjectId = mBean.getObjectId();

                        mBean.setObjectId(mObjectId);
                        mBean.delete(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    item.setIcon(R.mipmap.fav_nor);
                                    Toast.makeText(NewsContentActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                                    item.setTitle("收藏");
                                } else {
                                    Log.e("Aaaaaaaaaaaaaa", "done: " + e.toString());
                                }
                            }
                        });



                        /*save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    item.setIcon(R.mipmap.fav_nor);
                                    Toast.makeText(NewsContentActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                                    item.setTitle("收藏");
                                }
                            }
                        });*/
                    }
                } else {
                    Toast.makeText(NewsContentActivity.this, "请先登录", Toast.LENGTH_SHORT).show();

                }

                break;

            case R.id.font:
                showChooseDialog();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, mMyIUiListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, mMyIUiListener);
            }
        }
    }

   /* public void shareToQQ() {
        params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "标题");// 标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");// 摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com/news/1.html");// 内容地址
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");// 网络图片地址　　params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "应用名称");// 应用名称
        params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
        // 分享操作要在主线程中完成
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mTencent.shareToQQ(NewsContentActivity.this, params, mMyIUiListener);
            }
        });

    }*/


    public void shareToWX() {

    }

    //点击评论的时候，才显示评论输入框
    public static void showSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }


    private void showChooseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = new String[]{"超大号字体", "大号字体", "正常字体", "小号字体", "超小号字体"};
        builder.setTitle("字体判断");
        builder.setSingleChoiceItems(items, mCurrentItem, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCurrentChooseItem = which;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            WebSettings settings = tx_webView.getSettings();

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (mCurrentChooseItem) {
                    case 0:
                        //就是通过设置settings的setTextSize来改变字体的大小
                        settings.setTextSize(WebSettings.TextSize.LARGEST);
                        break;
                    case 1:
                        settings.setTextSize(WebSettings.TextSize.LARGER);
                        break;
                    case 2:
                        settings.setTextSize(WebSettings.TextSize.NORMAL);
                        break;
                    case 3:
                        settings.setTextSize(WebSettings.TextSize.SMALLER);
                        break;
                    case 4:
                        settings.setTextSize(WebSettings.TextSize.SMALLEST);
                        break;

                    default:
                        break;
                }
                //保存用户选择的状态
                mCurrentItem = mCurrentChooseItem;
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
