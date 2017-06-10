package com.endless.enldess_news.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.endless.enldess_news.R;
import com.endless.enldess_news.gson.DailyForecast;
import com.endless.enldess_news.gson.NewWeather;
import com.endless.enldess_news.gson.Weathers;
import com.endless.enldess_news.ui.fragment.NewFragment;
import com.endless.enldess_news.utils.HttpUtil;
import com.endless.enldess_news.utils.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/27.
 */

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ca_title_text)
    TextView mCaTitleText;
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
    private NewFragment mViewPagerFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private ScrollView mWeather_layout;
    private TextView mTitle_city;
    private TextView mTitle_update_time;
    private TextView mDegree_text;
    private TextView mWeather_info_text;
    private LinearLayout mForecast_layout;
    private TextView mApi_text;
    private TextView mPm2_5_text;
    private TextView mComfort_text;
    private TextView mWash_text;
    private TextView mSport_text;
    public DrawerLayout mDrawerLayout;
    private Button mNavButton;
    public SwipeRefreshLayout mSwipe_refresh;
    private String mWeatherId;
    private ImageView mPic_img;
    private String mWeatherString;
    private String mPic_img1;
    private static boolean isExit = false;
    private List<DailyForecast> list = new ArrayList<DailyForecast>();
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    private NewWeather mNewWeather = new NewWeather();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setUi();
        setContentView(R.layout.lamp_fragment_main_layout);
        initView();
        ButterKnife.bind(this);
        mToolbar.setTitle("");
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mCaTitleText.setText("天气预报");
        mBottomLampLayout.setSelected(true);
        initlisten();
        init();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavButton = (Button) findViewById(R.id.nav_button);
        mNavButton.setOnClickListener(mOnClickListener);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        mWeatherString = preferences.getString("weather", null);
        mPic_img1 = preferences.getString("pic", null);
        decidePicExist();
        decideWeatherExist();
        mSwipe_refresh.setOnRefreshListener(mOnRefreshListener);
        initdaily(mWeatherId);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
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


    private void init() {
        setSupportActionBar(mToolbar);
    }


    private void initlisten() {
        mBottomNewLayout.setOnClickListener(this);
        mBottomReadLayout.setOnClickListener(this);
        mBottomVideoLayout.setOnClickListener(this);
        mBottomLampLayout.setOnClickListener(this);
        mBottomPcLayout.setOnClickListener(this);
    }


    public void requestWeather(String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=671286e873d343169d6844775cbaf95a";
        mWeatherId = weatherId;
        initdaily(mWeatherId);
        Log.e("aaaaaa", "requestWeather: " + weatherUrl);
        HttpUtil.senOkHttpRequest(weatherUrl, mCallback);
        loadpic();

    }

    private void load() {

        Log.e("aaaaaaaaa", "load: " + list.size());

    }

    private void loadpic() {
        String request = "http://guolin.tech/api/bing_pic";
        HttpUtil.senOkHttpRequest(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String pic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("pic", pic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(pic).into(mPic_img);
                    }
                });
            }
        });
    }

    private void showWeatherInfo(Weathers weathers) {
        String cityName = weathers.getBasic().getCity();
        String updateTime = weathers.getBasic().getUpdate().getLoc().split(" ")[1];
        String degree = weathers.getNow().getTmp() + "℃";
        String weatherInfo = weathers.getNow().getCond().getTxt();
        Log.i("ccccccccccc", "showWeatherInfo: " + cityName + updateTime + degree + weatherInfo);

        mTitle_city.setText(cityName);
        mTitle_update_time.setText(updateTime);
        mDegree_text.setText(degree);
        mWeather_info_text.setText(weatherInfo);
        mForecast_layout.removeAllViews();

        for (DailyForecast forecast : list) {
            View view = LayoutInflater.from(this).from(this).inflate(R.layout.forecast_item, mForecast_layout, false);
            TextView dataText = (TextView) view.findViewById(R.id.data_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);
            dataText.setText(forecast.date);
            infoText.setText(forecast.more.info);
            maxText.setText(forecast.temperture.max);
            minText.setText(forecast.temperture.min);
            mForecast_layout.addView(view);
        }


        if (weathers.getAqi() != null) {
            mApi_text.setText(weathers.getAqi().getCity().getAqi());
            mPm2_5_text.setText(weathers.getAqi().getCity().getPm25());
        }
        String comfort = "舒适度:" + weathers.getSuggestion().getComf().getTxt();
        String carWash = "洗车指数:" + weathers.getSuggestion().getCw().getTxt();
        String sport = "运动指数:" + weathers.getSuggestion().getSport().getTxt();
        mComfort_text.setText(comfort);
        mWash_text.setText(carWash);
        mSport_text.setText(sport);
        mWeather_layout.setVisibility(View.VISIBLE);
    }

    private void initView() {
        mForecast_layout = (LinearLayout) findViewById(R.id.forecast_layout);
        mWeather_layout = (ScrollView) findViewById(R.id.weather_layout);
        mTitle_city = (TextView) findViewById(R.id.title_city);
        mTitle_update_time = (TextView) findViewById(R.id.title_update_time);
        mDegree_text = (TextView) findViewById(R.id.degree_text);
        mWeather_info_text = (TextView) findViewById(R.id.weather_info_text);
        mApi_text = (TextView) findViewById(R.id.api_text);
        mPm2_5_text = (TextView) findViewById(R.id.pm2_5_text);
        mComfort_text = (TextView) findViewById(R.id.comfort_text);
        mWash_text = (TextView) findViewById(R.id.wash_text);
        mSport_text = (TextView) findViewById(R.id.sport_text);
        mSwipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        mPic_img = (ImageView) findViewById(R.id.pic_img);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    };

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            requestWeather(mWeatherId);
        }
    };

    private Callback mCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WeatherActivity.this, "onFailure获取天气信息失败", Toast.LENGTH_LONG).show();
                    Log.i("AAAAA", "run: " + "onFailure获取天气信息失败");
                    mSwipe_refresh.setRefreshing(false);
                }
            });
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String responseText = response.body().string();
            final Weathers weather = Utility.handleWeatherResponse(responseText);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (weather != null && "ok".equals(weather.getStatus())) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                        showWeatherInfo(weather);
                        load();
                    } else {
                        Log.i("AAAAA", "获取天气信息失败");
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_LONG).show();
                    }
                    mSwipe_refresh.setRefreshing(false);
                }
            });
        }
    };


    private void decideWeatherExist() {


        mWeatherId = getIntent().getStringExtra("weather_id");
        if (mWeatherId == null) {
            mWeatherId = "CN101280101";
        }
        mWeather_layout.setVisibility(View.INVISIBLE);
        requestWeather(mWeatherId);

    }

    private void initdaily(String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=671286e873d343169d6844775cbaf95a";
        mWeatherId = weatherId;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(weatherUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("aaaaaa", "requestWeather: ssssssssssssss" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    final String responseText = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseText);
                    JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
                    String s = jsonArray.getJSONObject(0).toString();
                    Gson gson = new Gson();
                    mNewWeather = gson.fromJson(s, NewWeather.class);
                    list.clear();
                    list.addAll(mNewWeather.forecastList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void decidePicExist() {
        if (mPic_img1 != null) {
            Glide.with(WeatherActivity.this).load(mPic_img1).into(mPic_img);
        } else {
            loadpic();
        }
    }

    @Override
    public void onClick(View v) {

        mBottomReadLayout.setSelected(false);
        mBottomVideoLayout.setSelected(false);
        mBottomLampLayout.setSelected(false);
        mBottomPcLayout.setSelected(false);
        mBottomNewLayout.setSelected(false);

        switch (v.getId()) {
            case R.id.bottom_new_layout:
                mBottomNewLayout.setSelected(true);
                Intent intent1 = new Intent(this, MyMainActivity.class);
                intent1.putExtra("fragment", "news");
                startActivity(intent1);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;

            case R.id.bottom_read_layout:
                mBottomReadLayout.setSelected(true);
                Intent intent2 = new Intent(this, MyMainActivity.class);
                intent2.putExtra("fragment", "read");
                startActivity(intent2);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;

            case R.id.bottom_video_layout:
                mBottomVideoLayout.setSelected(true);
                Intent intent3 = new Intent(this, MyMainActivity.class);
                intent3.putExtra("fragment", "video");
                startActivity(intent3);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;

            case R.id.bottom_lamp_layout:
                mBottomLampLayout.setSelected(true);
                break;

            case R.id.bottom_pc_layout:
                mBottomPcLayout.setSelected(true);
                Intent intent4 = new Intent(this, MyMainActivity.class);
                intent4.putExtra("fragment", "pc");
                startActivity(intent4);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;

        }

    }
}
