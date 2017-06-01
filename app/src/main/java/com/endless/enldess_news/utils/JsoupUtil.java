package com.endless.enldess_news.utils;


import com.endless.enldess_news.bean.Score;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析获取的html内容的工具类
 */
public class JsoupUtil {
    private static JsoupUtil mJsoupUtil;

    private String[] time;

    private JsoupUtil() {
        time = new String[] { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };
    }

    private static JsoupUtil getInstance() {
        if (mJsoupUtil == null) {
            synchronized (OkHttpUtil.class) {
                if (mJsoupUtil == null) {
                    mJsoupUtil = new JsoupUtil();
                }
            }
        }
        return mJsoupUtil;
    }

    private Map<String, String> _getViewStateValue(String html) {
        Map<String, String> viewStateValue = new LinkedHashMap<>();
        if (null != html) {
            Document document = Jsoup.parse(html);
            Element viewstateElement = document.select("input[name=\"__VIEWSTATE\"]").first();
            Element viewstateGeneratorElement = document.select("input[name=\"__VIEWSTATEGENERATOR\"]").first();
            if (null != viewstateElement) {
                viewStateValue.put(Constants.LOGIN_BODY_NAME_VIEWSTATE, viewstateElement.attr("value"));
            }
            if (null != viewstateGeneratorElement) {
                viewStateValue.put(Constants.LOGIN_BODY_NAME_VIEWSTATEGENERATOR, viewstateGeneratorElement.attr("value"));
            }
        }
        return viewStateValue;
    }

    private Map<String, String> _getNameOrFailedInfo(String html) {
        Map<String, String> returnInfo = new LinkedHashMap<>();
        if (null != html) {
            Document document = Jsoup.parse(html);
            Element nameElement = document.getElementById("xhxm");
            if (null != nameElement) {
                String studentName = nameElement.html();
                Pattern p = Pattern.compile("(.+)[^同学]");
                Matcher m = p.matcher(studentName);
                if(m.find()) {
                    returnInfo.put(Constants.STUDENTNAME, m.group());
                }
            } else { // 找不到学生姓名，说明登录失败，跳转回了登录界面。这里取得登录失败的原因后返回
                Element infoElement = document.select("script[defer]").last();
                if (null != infoElement) {
                    String login_failed_info = infoElement.html();
                    Pattern p = Pattern.compile("([\\u4E00-\\u9FA5]+)");
                    Matcher m = p.matcher(login_failed_info);
                    if(m.find()) {
                        returnInfo.put(Constants.FAILEDINFO, m.group());
                    }
                }
            }
        }
        return returnInfo;
    }

    private List<Map<String, String>> _getCourseList(String html) {
        List<Map<String, String>> courseList =  new ArrayList<>();

        if (null == html) {
            return courseList;
        }

        Document document = Jsoup.parse(html);
        Elements table = document.getElementsByTag("table");
        Element data = table.get(1);

        Elements tr = data.getElementsByTag("tr");

        int index = 0;
        for (int i = 2; i <= tr.size() - 2; i += 2) {
            Elements td = tr.get(i).getElementsByTag("td");
            Elements course = td.select("td[align=\"Center\"]");

            Map<String, String> map = new HashMap<String, String>();
            for (int j = 0; j < course.size(); j++) {
                String content = course.get(j).text();
                if (!content.equals("")) {
                    map.put(time[index], content);
                }
                index++;
            }
            courseList.add(map);
            index = 0;
        }
        return courseList;
    }

    public List<Score> _getScoreList(String html) {
        List<Score> scoreList = new ArrayList<>();

        if (null == html) {
            return scoreList;
        }

        Document document = Jsoup.parse(html);
        Elements tables = document.select("table[class=\"datelist\"]");
        Element datas = tables.get(0);
        Elements trs = datas.getElementsByTag("tr");

        boolean first = true;
        for (int i = 0; i < trs.size(); i++) {
            Elements tds = trs.get(i).getElementsByTag("td");

            Score score = new Score();
            for (int j = 0; j < tds.size(); j++) {
                String content = tds.get(j).text();
                if(j == 1) {
                    score.setSubject(content);
                } else if(j == 2) {
                    score.setCategory(content);
                } else if(j == 3) {
                    if(first) {
                        score.setScore("成绩");
                    } else {
                        score.setScore(content);
                    }
                } else if(j == 4) {
                    if(first) {
                        score.setFinalScore("总评成绩");
                        first = false;
                    } else {
                        score.setFinalScore(content);
                    }
                } else if(j == 8) {
                    score.setCredit(content);
                } else if(j == 9) {
                    score.setGradePoint(content);
                } else continue;
            }
            scoreList.add(score);
        }
        return scoreList;
    }



    public static Map<String, String> getViewStateValue(String html) {
        return getInstance()._getViewStateValue(html);
    }

    public static Map<String, String> getNameOrFailedInfo(String html) {
       return getInstance()._getNameOrFailedInfo(html);
    }

    public static List<Map<String, String>> getCourseList(String html) {
        return getInstance()._getCourseList(html);
    }

    public static List<Score> getScoreList(String html) {
        return getInstance()._getScoreList(html);
    }
}
