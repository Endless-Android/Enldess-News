package com.endless.enldess_news.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.endless.enldess_news.R;
import com.endless.enldess_news.View.AfternoonCourseContents;
import com.endless.enldess_news.View.MorningCourseContents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScheduleActivity extends AppCompatActivity {

    private List<Map<String, String>> list = null;
    private ScrollView scrollView;
    private LinearLayout layout_content;
    private MorningCourseContents[] morningCourseCotents;
    private AfternoonCourseContents afternoonCourseContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();// 去掉标题栏
        setContentView(R.layout.activity_schedule);
        TextView text = (TextView) findViewById(R.id.ca_title_text);
        text.setText("大广交课程表");
        init();
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * 通过调用这个方法启动ScheduleActivity
     */
    public static void actionStart(Context context, List<Map<String, String>> courseList) {
        Intent intent = new Intent(context, ScheduleActivity.class);
        intent.putExtra("Course", (Serializable) courseList);
        context.startActivity(intent);
    }

    private void init() {
        Intent intent = this.getIntent();
        list = (ArrayList<Map<String, String>>) intent.getSerializableExtra("Course");
    }

    private void initView() {
        scrollView = (ScrollView) findViewById(R.id.course_scrollview);
        scrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        layout_content = (LinearLayout) findViewById(R.id.layout_schedule_content);
        morningCourseCotents = new MorningCourseContents[list.size() - 1];
        afternoonCourseContents = new AfternoonCourseContents(ScheduleActivity.this);

        for (int i = 0; i < list.size(); i++) {
            if (i == (list.size() - 1)) {
                afternoonCourseContents.setFirstAfternoonCourseText("7");
                afternoonCourseContents.setSecondAfternoonCourseText("8");
                afternoonCourseContents.setMondayAfternoonCourseText(list.get(3).get("周一"));
                afternoonCourseContents.setTuesdayAfternoonCourseText(list.get(3).get("周二"));
                afternoonCourseContents.setWednesdayAfternoonCourseText(list.get(3).get("周三"));
                afternoonCourseContents.setThursdayAfternoonCourseText(list.get(3).get("周四"));
                afternoonCourseContents.setFridayAfternoonCourseText(list.get(3).get("周五"));
                layout_content.addView(afternoonCourseContents);
            } else {
                morningCourseCotents[i] = new MorningCourseContents(ScheduleActivity.this);
                if (i == 0) {
                    morningCourseCotents[i].setFirstMorningCourseText(String.valueOf("1"));
                    morningCourseCotents[i].setSecondMorningCourseText(String.valueOf("2"));
                } else {
                    morningCourseCotents[i].setFirstMorningCourseText(String.valueOf((i * 2 + 1)));
                    morningCourseCotents[i].setSecondMorningCourseText(String.valueOf((i * 2 + 2)));
                }
                morningCourseCotents[i].setMondayMorningCourseText(list.get(i).get("周一"));
                morningCourseCotents[i].setTuesdayMorningCourseText(list.get(i).get("周二"));
                morningCourseCotents[i].setWednesdayMorningCourseText(list.get(i).get("周三"));
                morningCourseCotents[i].setThursdayMorningCourseText(list.get(i).get("周四"));
                morningCourseCotents[i].setFridayMorningCourseText(list.get(i).get("周五"));
                layout_content.addView(morningCourseCotents[i]);
            }
        }
    }
}
