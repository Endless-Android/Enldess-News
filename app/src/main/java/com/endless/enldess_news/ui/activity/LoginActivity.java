package com.endless.enldess_news.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.endless.enldess_news.R;
import com.endless.enldess_news.bean.Score;
import com.endless.enldess_news.utils.Constants;
import com.endless.enldess_news.utils.JsoupUtil;
import com.endless.enldess_news.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity {

    private Handler handler;
    private Context context;

    private TextView tv_title;
    private Button btn_login, btn_cancel;
    private EditText et_username, et_password, et_verificationCode;
    private CheckBox cb_rememberPassword;
    private ImageView iv_VerificationCode, iv_reload;

    private ProgressDialog progressDialog;

    /**
     * 要查询的信息的类型
     */
    private int type;
    /**
     * 验证码图片数据
     */
    private byte[] verificationCode;
    /**
     * 登录状态
     */
    private static boolean isLogin = false;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * url编码的学生姓名
     */
    private static String urlEncodeStudentName = null;
    /**
     * 登录的用户名， 即学号
     */
    private static String userName;
    /**
     * 用于存放请求头和请求参数
     */
    private static Map<String, String> requestHeadersMap, loginRequestBodyMap, scheduleRequestBodyMap, scoreRequestBodyMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initIntent();
        initRequestData();
        initViews();
        initEvents();
    }

    /**
     * 通过调用这个方法启动LoginActivity
     */
    public static void actionStart(Context context, int type, byte[] verificationCode) {
        Intent intent = new Intent(context,LoginActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("verificationCode", verificationCode);
        context.startActivity(intent);
    }

    private void initIntent() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        verificationCode = intent.getByteArrayExtra("verificationCode");
    }

    private void initRequestData() {
        requestHeadersMap = new LinkedHashMap<String, String>();
        loginRequestBodyMap = new LinkedHashMap<>();
        scheduleRequestBodyMap = new LinkedHashMap<>();
        scoreRequestBodyMap = new LinkedHashMap<>();

        requestHeadersMap.put(Constants.HEADER_NAME_HOST, Constants.HEADER_VALUE_HOST);

        requestHeadersMap.put(Constants.HEADER_NAME_AGENT, Constants.HEADER_VALUE_AGENT);

        initLoginRequestBody();
        initScheduleRequestBody();
        initScoreRequestBody();
    }



    private void initLoginRequestBody() {
        OkHttpUtil.getAsync(Constants.EDUCATION_SYSTEM_LOGIN_URL, new OkHttpUtil.ResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_VIEWSTATE, Constants.LOGIN_BODY_VALUE_VIEWSTATE);
                loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_VIEWSTATEGENERATOR, Constants.LOGIN_BODY_VALUE_VIEWSTATEGENERATOR);
            }

            @Override
            public void onResponse(byte[] response) {
                if (null == response) {
                    loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_VIEWSTATE, Constants.LOGIN_BODY_VALUE_VIEWSTATE);
                    loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_VIEWSTATEGENERATOR, Constants.LOGIN_BODY_VALUE_VIEWSTATEGENERATOR);
                }
                String result = null;
                String viewState = null;
                String viewStateGenerator = null;
                try {
                    result = new String(response, "gb2312");
                    Map<String, String> viewStateValue = JsoupUtil.getViewStateValue(result);
                    viewState = viewStateValue.get(Constants.LOGIN_BODY_NAME_VIEWSTATE);
                    viewStateGenerator = viewStateValue.get(Constants.LOGIN_BODY_NAME_VIEWSTATEGENERATOR);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } finally {
                    if (null != viewState) {
                        loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_VIEWSTATE, viewState);
                    } else {
                        loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_VIEWSTATE, Constants.LOGIN_BODY_VALUE_VIEWSTATE);
                    }
                    if (null != viewStateGenerator) {
                        loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_VIEWSTATEGENERATOR, viewStateGenerator);
                    } else {
                        loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_VIEWSTATEGENERATOR, Constants.LOGIN_BODY_VALUE_VIEWSTATEGENERATOR);
                    }
                }
            }
        });
        loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_BUTTON1, Constants.LOGIN_BODY_VALUE_BUTTON1);
        loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_HIDPDRS, Constants.LOGIN_BODY_VALUE_HIDPDRS);
        loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_HIDSC, Constants.LOGIN_BODY_VALUE_HIDSC);
        loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_LANGUAGE, Constants.LOGIN_BODY_VALUE_LANGUAGE);
        loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_TYPE, Constants.LOGIN_BODY_VALUE_TYPE);
    }

    private void initScheduleRequestBody() {
        scheduleRequestBodyMap.put(Constants.SCHEDULE_BODY_NAME_EVENTARGUMENT, Constants.SCHEDULE_BODY_VALUE_EVENTARGUMENT);
        scheduleRequestBodyMap.put(Constants.SCHEDULE_BODY_NAME_EVENTTARGET, Constants.SCHEDULE_BODY_VALUE_EVENTTARGET);
        scheduleRequestBodyMap.put(Constants.SCHEDULE_BODY_NAME_VIEWSTATE, Constants.SCHEDULE_BODY_VALUE_VIEWSTATE);
        scheduleRequestBodyMap.put(Constants.SCHEDULE_BODY_NAME_VIEWSTATEGENERATOR, Constants.SCHEDULE_BODY_VALUE_VIEWSTATEGENERATOR);
        scheduleRequestBodyMap.put(Constants.SCHEDULE_BODY_NAME_SCHOOLYEAR, MainActivity.getSelectedSchoolYearValue());
        scheduleRequestBodyMap.put(Constants.SCHEDULE_BODY_NAME_TERM, MainActivity.getSelectedTermValue());
    }

    private void initScoreRequestBody() {
        scoreRequestBodyMap.put(Constants.SCORE_BODY_NAME_VIEWSTATE, Constants.SCORE_BODY_VALUE_VIEWSTATE);
        scoreRequestBodyMap.put(Constants.SCORE_BODY_NAME_VIEWSTATEGENERATOR, Constants.SCORE_BODY_VALUE_VIEWSTATEGENERATOR);
        scoreRequestBodyMap.put(Constants.SCORE_BODY_NAME_BUTTON1, Constants.SCORE_BODY_VALUE_BUTTON1);
        scoreRequestBodyMap.put(Constants.SCORE_BODY_NAME_SCHOOLYEAR, MainActivity.getSelectedSchoolYearValue());
        scoreRequestBodyMap.put(Constants.SCORE_BODY_NAME_TERM, MainActivity.getSelectedTermValue());
        scoreRequestBodyMap.put(Constants.SCORE_BODY_NAME_TXTQSCJ, Constants.SCORE_BODY_VALUE_TXTQSCJ);
        scoreRequestBodyMap.put(Constants.SCORE_BODY_NAME_TXTZZCJ, Constants.SCORE_BODY_VALUE_TXTZZCJ);
    }

    private void initViews() {
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        btn_login = (Button) this.findViewById(R.id.btn_login);
        btn_cancel = (Button) this.findViewById(R.id.btn_cancel);
        et_username = (EditText) this.findViewById(R.id.et_inputUsername);
        et_password = (EditText) this.findViewById(R.id.et_inputPassword);
        et_verificationCode = (EditText) this.findViewById(R.id.et_inputVerificationCode);
        iv_VerificationCode = (ImageView) this.findViewById(R.id.iv_VerificationCode);
        iv_reload = (ImageView) this.findViewById(R.id.iv_reload);
        cb_rememberPassword = (CheckBox) this.findViewById(R.id.cb_rememberPassword);

        setVerificationCodeBg();
    }

    private void initEvents() {

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = et_username.getText().toString();
                requestHeadersMap.put(Constants.HEADER_NAME_REFERER, Constants.HEADER_VALUE_REFERER + userName);

                loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_USERNAME, userName);
                loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_PASSWORD, et_password.getText().toString());
                loginRequestBodyMap.put(Constants.LOGIN_BODY_NAME_SECRETCODE, et_verificationCode.getText().toString());

                OkHttpUtil.postAsync(Constants.EDUCATION_SYSTEM_LOGIN_URL, new OkHttpUtil.ResultCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        setLoginState(false);
                        Toast.makeText(LoginActivity.this, "登录失败!", Toast.LENGTH_SHORT).show();
                        LoginActivity.this.finish();
                    }

                    @Override
                    public void onResponse(byte[] response) {
                        if (null != response) {
                            parseResponseFromLogin(response);
                            LoginActivity.this.finish();
                            if (isLoginSuccessful()) {
                                switch (type) {
                                    case Constants.SEARCH_SCHEDULE:
                                        searchScheduleOperation(LoginActivity.this);
                                        break;
                                    case Constants.SEARCH_SCORE:
                                        searchScoreOperation(LoginActivity.this);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "服务器错误，请重试!", Toast.LENGTH_SHORT).show();
                            LoginActivity.this.finish();
                        }
                    }
                }, loginRequestBodyMap, requestHeadersMap);
            }
        });

        iv_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtil.getAsync(Constants.VERIFICATION_CODE_URL, new OkHttpUtil.ResultCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        verificationCode = null;
                        setVerificationCodeBg();
                    }

                    @Override
                    public void onResponse(byte[] response) {
                        verificationCode = response;
                        setVerificationCodeBg();
                    }
                });
            }
        });
    }

    /**
     * 设置验证码图片
     */
    private void setVerificationCodeBg() {
        if (verificationCode != null && verificationCode.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(verificationCode, 0, verificationCode.length);
            Bitmap resizeBitmap = changeBitmapSize(bitmap, 140, 60);
            //iv_VerificationCode.setImageBitmap(resizeBitmap);
            iv_VerificationCode.setBackground(new BitmapDrawable(getResources(), resizeBitmap));
            verificationCode = null;
        } else {
            iv_VerificationCode.setBackgroundResource(R.mipmap.loading_failed);
        }
    }

    /**
     * 用于改变验证码图片尺寸
     */
    private Bitmap changeBitmapSize(Bitmap bitmap,float width,float height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleX = (float) width / w;
        float scaleY = (float) height / h;
        matrix.postScale(scaleX, scaleY);
        Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return resizeBitmap;
    }

    /**
     * 通过解析post请求返回的数据，判断是否登录成功了
     */
    private void parseResponseFromLogin(byte[] response) {
        setLoginState(false);
        String result = null;
        String outputInfo = null;
        try {
            result = new String(response, "gb2312");
            Log.e("test11", result);
            Map<String, String> returnInfo = JsoupUtil.getNameOrFailedInfo(result);
            studentName = returnInfo.get(Constants.STUDENTNAME);
            Log.e("test", "parseResponseFromLogin: "+studentName );
            if (null != studentName) {
                // 将gb2312编码的学生姓名转为url编码的字符串
                urlEncodeStudentName = URLEncoder.encode(studentName, "gb2312");
                outputInfo = "登录成功";
                setLoginState(true);
            } else {
                String failedInfo = returnInfo.get(Constants.FAILEDINFO);
                Log.e("test", "parseResponseFromLogin: "+failedInfo );
                if (null != failedInfo) {
                    outputInfo = failedInfo;
                } else {
                    outputInfo = "服务器错误，请重试!";
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            outputInfo = "服务器错误，请重试!";
        } finally {
            Toast.makeText(LoginActivity.this, outputInfo, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 设置登录状态
     */
    public static void setLoginState(boolean loginState) {
        isLogin = loginState;
    }

    /**
     * 返回当前登录状态
     */
    public static boolean isLoginSuccessful() {
        return isLogin;
    }

    /**
     * 查询课表的一系列操作
     */
    public static void searchScheduleOperation(final Context context) {
        // 重新拼接好网址
        String newScheduleUrl = Constants.SEARCH_SCHEDULE_URL
                .replace(Constants.LOGIN_BODY_NAME_USERNAME, userName)
                .replace(Constants.STUDENTNAME, urlEncodeStudentName);


        // 这里需要注意，OkHttp这里设置requestHeader的Referer时，如果出现中文，会报错。所以之前就对学生姓名进行了url编码
        requestHeadersMap.put(Constants.HEADER_NAME_REFERER, newScheduleUrl);
        Log.e("aaaaaaaaaaaaaaaa", "searchScheduleOperation: "+newScheduleUrl);
        scheduleRequestBodyMap.put(Constants.SCHEDULE_BODY_NAME_SCHOOLYEAR, MainActivity.getSelectedSchoolYearValue());
        scheduleRequestBodyMap.put(Constants.SCHEDULE_BODY_NAME_TERM, MainActivity.getSelectedTermValue());

        // 这里的请求地址和Referer一样，不过这里学生姓名直接用中文也没问题。就和之前的Referer保持一致了，也用那个使用率url编码的地址
        OkHttpUtil.postAsync(newScheduleUrl, new OkHttpUtil.ResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(context, "获取课表失败,请重试!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(byte[] response) {
                String message = null;
                try {
                    if (null != response) {
                        String result = new String(response, "gb2312");
                        Log.e("test课表", result);
                        List<Map<String, String>> courseList = JsoupUtil.getCourseList(result);
                        if (null != courseList) {
                            message = "获取课表成功!";
                            ScheduleActivity.actionStart(context, courseList);
                        } else {
                            message = "获取课表失败,请重试!";
                        }
                    } else {
                        message = "获取课表失败,请重试!";
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    message = "获取课表失败,请重试!";
                } finally {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }
        }, scheduleRequestBodyMap, requestHeadersMap);
    }

    /**
     * 查询成绩的一系列操作
     */
    public static void searchScoreOperation(final Context context) {
        // 重新拼接好网址
        String newScoreUrl = Constants.SEARCH_SCORE_URL
                .replace(Constants.LOGIN_BODY_NAME_USERNAME, userName)
                .replace(Constants.STUDENTNAME, urlEncodeStudentName);

        // 这里和查询课表一样的，之前就对学生姓名进行了url编码
        requestHeadersMap.put(Constants.HEADER_NAME_REFERER, newScoreUrl);

        scoreRequestBodyMap.put(Constants.SCORE_BODY_NAME_SCHOOLYEAR, MainActivity.getSelectedSchoolYearValue());
        scoreRequestBodyMap.put(Constants.SCORE_BODY_NAME_TERM, MainActivity.getSelectedTermValue());

        OkHttpUtil.postAsync(newScoreUrl, new OkHttpUtil.ResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(context, "获取成绩失败,请重试!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(byte[] response) {
                String message = null;
                try {
                    if (null != response) {
                        String result = new String(response, "gb2312");
                        Log.e("test成绩", result);
                        List<Score> scoreList = JsoupUtil.getScoreList(result);
                        if (null != scoreList) {
                            message = "获取成绩成功!";
                            ScoreActivity.actionStart(context, scoreList);
                        } else {
                            message = "获取成绩失败,请重试!";
                        }
                    } else {
                        message = "获取成绩失败,请重试!";
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    message = "获取成绩失败,请重试!";
                } finally {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }
        }, scoreRequestBodyMap, requestHeadersMap);
    }
}
