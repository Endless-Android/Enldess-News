package com.endless.enldess_news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.endless.enldess_news.R;
import com.endless.enldess_news.View.MyRecyclerView;
import com.endless.enldess_news.adapter.MyCollectAdapter;
import com.endless.enldess_news.bean.CollectBean;
import com.endless.enldess_news.config.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/5/21.
 */

public class CollectActivity extends AppCompatActivity {

    @BindView(R.id.coloect_recycleView)
    MyRecyclerView mColoectRecycleView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ca_title_text)
    TextView mCaTitleText;
    @BindView(R.id.coll_refresh)
    SwipeRefreshLayout mCollRefresh;
    private ArrayList<CollectBean> mCollectList;
    private MyCollectAdapter mMyCollectAdapter;
    private Handler mHandler = new Handler();
    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mMyCollectAdapter.notifyDataSetChanged();
            mCollRefresh.setRefreshing(false);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        mCollectList = new ArrayList<CollectBean>();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCaTitleText.setVisibility(View.GONE);
        mMyCollectAdapter = new MyCollectAdapter(this, mCollectList);
        initdate();
        initRecycleView();
        initRefresh();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initdate();
    }



    private void initRefresh() {
        mCollRefresh.setColorSchemeResources(R.color.colorPrimary);
        mCollRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreash();
            }
        });

    }

    private void refreash() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(mRunnable);

            }
        }).start();
    }

    private void initRecycleView() {
        mColoectRecycleView.setHasFixedSize(true);
        mColoectRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mColoectRecycleView.setAdapter(mMyCollectAdapter);
        mColoectRecycleView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CollectActivity.this, NewsContentActivity.class);
                intent.putExtra("url", mCollectList.get(position).getUrl());
                intent.putExtra("title", mCollectList.get(position).getTitle());
                intent.putExtra("imag", mCollectList.get(position).getImagrl());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                String objectId = mCollectList.get(position).getId();
                mCollectList.remove(position);
                CollectBean mBean = new CollectBean();
                mBean.setObjectId(objectId);
                mBean.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            mHandler.post(mRunnable);
                            Toast.makeText(CollectActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("Aaaaaaaaaaaaaa", "done: " + e.toString());
                        }
                    }
                });

            }
        });
    }

    private void initdate() {
        mCollectList.clear();
        BmobQuery<CollectBean> query = new BmobQuery<CollectBean>();
        query.addWhereEqualTo("iscoll", true);
        query.setLimit(50);
        query.findObjects(new FindListener<CollectBean>() {
            @Override
            public void done(List<CollectBean> object, BmobException e) {
                if (e == null) {
                    for (CollectBean collectBean : object) {
                        Log.e("77777777777777777777", "onCreate() returned: " + collectBean.getObjectId());
                        CollectBean bean = new CollectBean();
                        bean.setTime(collectBean.getTime());
                        bean.setTitle(collectBean.getTitle());
                        bean.setUrl(collectBean.getUrl());
                        bean.setImagrl(collectBean.getImagrl());
                        bean.setId(collectBean.getObjectId());
                        mCollectList.add(bean);
                    }
                    mHandler.post(mRunnable);
                    mMyCollectAdapter.notifyDataSetChanged();
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
