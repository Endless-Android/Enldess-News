package com.endless.enldess_news.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.endless.enldess_news.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 周一至周五下午的课程布局
 */
public class AfternoonCourseContents extends LinearLayout {

    private TextView tv_first_afternoon_course, tv_second_afternoon_course, tv_third_afternoon_course,
            tv_afternoon_course_monday, tv_afternoon_course_tuesday, tv_afternoon_course_wednesday,
            tv_afternoon_course_thursday, tv_afternoon_course_friday;
    /**
     * 用于分离字段
     */
    private String[] s;
    private int center = 0;
    /**
     * 用于一格同时有两节课时
     */
    private Map<String, String> map = new HashMap<String, String>();
    private boolean flag = false;

    public AfternoonCourseContents(Context context) {
        this(context, null);
    }

    public AfternoonCourseContents(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AfternoonCourseContents(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.afternoon_course_contents, this);

        tv_first_afternoon_course = (TextView) view.findViewById(R.id.tv_first_afternoon_course);
        tv_second_afternoon_course = (TextView) view.findViewById(R.id.tv_second_afternoon_course);
        tv_afternoon_course_monday = (TextView) view.findViewById(R.id.tv_afternoon_course_monday);
        tv_afternoon_course_tuesday = (TextView) view.findViewById(R.id.tv_afternoon_course_tuesday);
        tv_afternoon_course_wednesday = (TextView) view.findViewById(R.id.tv_afternoon_course_wednesday);
        tv_afternoon_course_thursday = (TextView) view.findViewById(R.id.tv_afternoon_course_thursday);
        tv_afternoon_course_friday = (TextView) view.findViewById(R.id.tv_afternoon_course_friday);
    }

    public void setFirstAfternoonCourseText(String str) {
        tv_first_afternoon_course.setText(str);
    }

    public void setSecondAfternoonCourseText(String str) {
        tv_second_afternoon_course.setText(str);
    }


    public void setMondayAfternoonCourseText(String str) {
        final String newStr = this.filter(str, "MondayAfternoonCourse");
        tv_afternoon_course_monday.setText(newStr);
        if (map.get("MondayAfternoonCourse") != null && s.length > 6) {
            tv_afternoon_course_monday.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!flag) {
                        tv_afternoon_course_monday.setText(map.get("MondayAfternoonCourse"));
                        flag = true;
                    } else {
                        tv_afternoon_course_monday.setText(newStr);
                        flag = false;
                    }
                }
            });
        }
        if (newStr == null) {
            tv_afternoon_course_monday.setBackgroundResource(R.drawable.course_empty_background);
        }
    }

    public void setTuesdayAfternoonCourseText(String str) {
        final String newStr = this.filter(str, "TuesdayAfternoonCourse");
        tv_afternoon_course_tuesday.setText(newStr);
        if (map.get("TuesdayAfternoonCourse") != null && s.length > 6) {
            tv_afternoon_course_tuesday.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!flag) {
                        tv_afternoon_course_tuesday.setText(map.get("TuesdayAfternoonCourse"));
                        flag = true;
                    } else {
                        tv_afternoon_course_tuesday.setText(newStr);
                        flag = false;
                    }
                }
            });
        }
        if (newStr == null) {
            tv_afternoon_course_tuesday.setBackgroundResource(R.drawable.course_empty_background);
        }
    }

    public void setWednesdayAfternoonCourseText(String str) {
        final String newStr = this.filter(str, "WednesdayAfternoonCourse");
        tv_afternoon_course_wednesday.setText(newStr);
        if (map.get("WednesdayAfternoonCourse") != null && s.length > 6) {
            tv_afternoon_course_wednesday.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!flag) {
                        tv_afternoon_course_wednesday.setText(map.get("WednesdayAfternoonCourse"));
                        flag = true;
                    } else {
                        tv_afternoon_course_wednesday.setText(newStr);
                        flag = false;
                    }
                }
            });
        }
        if (newStr == null) {
            tv_afternoon_course_wednesday.setBackgroundResource(R.drawable.course_empty_background);
        }
    }

    public void setThursdayAfternoonCourseText(String str) {
        final String newStr = this.filter(str, "ThursdayAfternoonCourse");
        tv_afternoon_course_thursday.setText(newStr);
        if (map.get("ThursdayAfternoonCourse") != null && s.length > 6) {
            tv_afternoon_course_thursday.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!flag) {
                        tv_afternoon_course_thursday.setText(map.get("ThursdayAfternoonCourse"));
                        flag = true;
                    } else {
                        tv_afternoon_course_thursday.setText(newStr);
                        flag = false;
                    }
                }
            });
        }
        if (newStr == null) {
            tv_afternoon_course_thursday.setBackgroundResource(R.drawable.course_empty_background);
        }
    }

    public void setFridayAfternoonCourseText(String str) {
        final String newStr = this.filter(str, "FridayAfternoonCourse");
        tv_afternoon_course_friday.setText(newStr);
        if (map.get("FridayAfternoonCourse") != null && s.length > 6) {
            tv_afternoon_course_friday.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!flag) {
                        tv_afternoon_course_friday.setText(map.get("FridayAfternoonCourse"));
                        flag = true;
                    } else {
                        tv_afternoon_course_friday.setText(newStr);
                        flag = false;
                    }
                }
            });
        }
        if (newStr == null) {
            tv_afternoon_course_friday.setBackgroundResource(R.drawable.course_empty_background);
        }
    }

    /**
     * 通过正则表达式，对信息进行筛选
     */
    public String filter(String str, String viewName) {
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5]+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            s = str.split(" ");

            Pattern p1 = Pattern.compile("(\\{.+\\})");
            Pattern p2 = Pattern.compile("([\\u4E00-\\u9FA5]+)[\\(]*");
            Matcher m1;

            m1 = p1.matcher(s[1]);
            if (m1.find())
                s[1] = m1.group(1);

            m1 = p2.matcher(s[2]);
            if (m1.find())
                s[2] = m1.group(1);

            if (s.length > 6) {
                if (s.length / 2 == 6 || s.length / 2 == 4)
                    center = s.length / 2;
                m1 = p1.matcher(s[center + 1]);
                if (m1.find())
                    s[center + 1] = m1.group(1);

                m1 = p2.matcher(s[center + 2]);
                if (m1.find())
                    s[center + 2] = m1.group(1);
            }

            StringBuffer sb1 = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            sb1.append(s[0] + "\n");
            sb1.append(s[1] + "\n");
            sb1.append(s[2] + "\n");
            sb1.append(s[3] + "\n");
            if (s.length > 6) {
                sb1.append("\n[点击切换]");
                sb2.append(s[center] + "\n");
                sb2.append(s[center + 1] + "\n");
                sb2.append(s[center + 2] + "\n");
                sb2.append(s[center + 3] + "\n");
                sb2.append("\n[点击切换]");
                map.put(viewName, (sb2.toString()));
            }

            return sb1.toString();
        } else
            return null;
    }
}
