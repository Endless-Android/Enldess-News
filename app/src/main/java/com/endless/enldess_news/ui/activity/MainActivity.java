package com.endless.enldess_news.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.endless.enldess_news.R;
import com.endless.enldess_news.utils.AppData;
import com.endless.enldess_news.utils.Constants;
import com.endless.enldess_news.utils.MyCookieJar;
import com.endless.enldess_news.utils.OkHttpUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bottom_new_layout)
    LinearLayout mBottomNewLayout;
    @BindView(R.id.bottom_read_layout)
    LinearLayout mBottomReadLayout;
    @BindView(R.id.bottom_video_layout)
    LinearLayout mBottomVideoLayout;
    @BindView(R.id.bottom_lamp_layout)
    LinearLayout mBottomLampLayout;
    @BindView(R.id.bottom_pc_layout)
    LinearLayout mBottomPcLayout;
    private Menu mMenu;     //菜单
    private Button btn_searchSchedule, btn_searchScore;     //课程表。成绩单
    private static Spinner sp_school_year;      //入学年份
    private static Spinner sp_term;     //月份
    private int mSearchType;
    private static boolean isExit = false;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBottomReadLayout.setSelected(true);
        getSupportActionBar().setTitle("大广交教务系统");
        initViews();
        initLEvents();

        if (AppData.getState(MainActivity.this)) { // 如果是第一次运行程序，需要先填写入学年份
            showSettingsDialog();
        } else {
            setTimeAdapter(); // 根据入学时间，设置spinner的内容
        }
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

    @Override
    protected void onResume() {
        super.onResume();

        if (LoginActivity.isLoginSuccessful()) {
            showLogout();
        } else {
            hideLogout();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                showSettingsDialog();
                return true;
            case R.id.menu_logout:
                MyCookieJar.resetCookies();
                LoginActivity.setLoginState(false);
                hideLogout();
                Toast.makeText(MainActivity.this, "已退出登录!", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 利用反射使menu的icon显示出来
     */
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "setting menu icons failed.", e);
                }
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int curId = v.getId();
        switch (curId) {
            case R.id.btn_searchSchedule:
                mSearchType = Constants.SEARCH_SCHEDULE;
                break;
            case R.id.btn_searchScore:
                mSearchType = Constants.SEARCH_SCORE;
                break;
            default:
                break;
        }

        if (curId == R.id.btn_searchSchedule || curId == R.id.btn_searchScore) {
            if (!LoginActivity.isLoginSuccessful()) {
                showLoginActivity();
            } else {
                switch (curId) {
                    case R.id.btn_searchSchedule:
                        LoginActivity.searchScheduleOperation(MainActivity.this);
                        break;
                    case R.id.btn_searchScore:
                        LoginActivity.searchScoreOperation(MainActivity.this);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 显示设置入学年份的对话框
     */
    private void showSettingsDialog() {
        final EditText inputTimeOfEnrollment = new EditText(this);
        inputTimeOfEnrollment.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputTimeOfEnrollment.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(getString(R.string.settings_dialog_title))
                .setIcon(R.mipmap.write)
                .setView(inputTimeOfEnrollment)
                .setNegativeButton(getString(R.string.settings_dialog_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                            field.setAccessible(true);

                            if (AppData.getTime(MainActivity.this) == null) {
                                // 利用反射，将mShowing变量设为false，对话框就不会自动关闭了
                                field.set(dialog, false);
                            } else {
                                field.set(dialog, true);
                                dialog.dismiss();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setPositiveButton(getString(R.string.settings_dialog_confirm), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                            field.setAccessible(true);
                            if (isTimeRight(inputTimeOfEnrollment)) {
                                if (AppData.getState(MainActivity.this)) {
                                    AppData.saveState(MainActivity.this, false);
                                }
                                AppData.saveTime(MainActivity.this, inputTimeOfEnrollment.getText().toString());
                                setTimeAdapter();
                                field.set(dialog, true);
                                dialog.dismiss();
                            } else {
                                field.set(dialog, false);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        dialogBuilder.show();
    }

    /**
     * 判断输入的入学年份是否正确
     */
    private boolean isTimeRight(EditText inputTimeOfEnrollment) {
        boolean isTimeRight = false;
        String message = null;
        if (!inputTimeOfEnrollment.getText().toString().equals("")) {
            if (inputTimeOfEnrollment.getText().toString().length() == 4) {
                Calendar c = Calendar.getInstance();//首先要获取日历对象
                int mYear = c.get(Calendar.YEAR); // 获取当前年份
                int time = Integer.parseInt(inputTimeOfEnrollment.getText().toString());
                if (time > mYear) {
                    isTimeRight = false;
                    message = "请输入正确的年份!";
                } else if (time < 2000) {
                    isTimeRight = false;
                    message = "暂不支持太久远的年份!";
                } else {
                    isTimeRight = true;
                    message = "设置成功!";
                }
            } else {
                isTimeRight = false;
                message = "请输入正确的年份!";
            }
        } else {
            isTimeRight = false;
            message = "入学年份不能为空!";
        }
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        return isTimeRight;
    }

    /**
     * 显示"退出登录"菜单项
     */
    private void showLogout() {
        if (mMenu == null) {
            return;
        }
        mMenu.getItem(1).setVisible(true);
    }

    /**
     * 隐藏"退出登录"菜单项
     */
    private void hideLogout() {
        if (mMenu == null) {
            return;
        }
        mMenu.getItem(1).setVisible(false);
    }

    private void initViews() {
        btn_searchSchedule = (Button) this.findViewById(R.id.btn_searchSchedule);
        btn_searchScore = (Button) this.findViewById(R.id.btn_searchScore);
        sp_school_year = (Spinner) this.findViewById(R.id.sp_school_year);
        sp_term = (Spinner) this.findViewById(R.id.sp_term);
    }

    private void initLEvents() {
        btn_searchSchedule.setOnClickListener(this);
        btn_searchScore.setOnClickListener(this);
    }

    private void showLoginActivity() {
        OkHttpUtil.getAsync(Constants.VERIFICATION_CODE_URL, new OkHttpUtil.ResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e("test", "Get Verification code failed." + e.toString());
                LoginActivity.actionStart(MainActivity.this, mSearchType, null);
            }

            @Override
            public void onResponse(byte[] response) {
                Log.e("test", "Get Verification code success.");
                LoginActivity.actionStart(MainActivity.this, mSearchType, response);
            }
        });
    }

    /**
     * 根据设置的入学时间设置学期时间spinner的内容
     */
    private void setTimeAdapter() {
        ArrayList<String> mList = new ArrayList<String>();
        String strTime = AppData.getTime(MainActivity.this);
        int time = Integer.parseInt(strTime);
        for (int i = 0; i < 4; i++) {
            mList.add((time + i) + "-" + (time + i + 1));
        }
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, mList);

        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_school_year.setAdapter(mAdapter);
    }

    public static String getSelectedSchoolYearValue() {
        return sp_school_year.getSelectedItem().toString();
    }

    public static String getSelectedTermValue() {
        return sp_term.getSelectedItem().toString();
    }

    @OnClick({R.id.bottom_new_layout, R.id.bottom_read_layout, R.id.bottom_video_layout, R.id.bottom_lamp_layout, R.id.bottom_pc_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottom_new_layout:
                mBottomNewLayout.setSelected(true);
                Intent intent1 = new Intent(this, MyMainActivity.class);
                intent1.putExtra("fragment", "news");
                startActivity(intent1);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;

            case R.id.bottom_read_layout:
                mBottomReadLayout.setSelected(true);
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
                Intent intent5 = new Intent(this, MyMainActivity.class);
                intent5.putExtra("fragment", "lamp");
                startActivity(intent5);
                overridePendingTransition(R.anim.hold, R.anim.fade);
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
