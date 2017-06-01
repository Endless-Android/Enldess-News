package com.endless.enldess_news.utils;

/**
 * 常量类
 */
public class Constants {
    // 用于SharedPreferences保存程序状态文件时
    public static final String APP_DATA = "app_data";
    public static final String FIRST_INSTALL = "first_install";
    public static final String TIME_OF_ENROLLMENT = "time_of_enrollment";


    // 要查询的信息的类型
    public static final int SEARCH_SCHEDULE = 1;
    public static final int SEARCH_SCORE = 2;

    // Jsoup解析后传递的Map所用的key值
    public static final String STUDENTNAME = "studentName";
    public static final String FAILEDINFO = "failedInfo";

    /**
     * 验证码网址
     */
    public static final String VERIFICATION_CODE_URL = "http://jw2012.gdcp.cn/CheckCode.aspx";
    /**
     * 教务系统登录界面网址
     */
    public static final String EDUCATION_SYSTEM_LOGIN_URL = "http://jw2012.gdcp.cn/default2.aspx";
    /**
     * 查询课表的网址
     */
    public static final String SEARCH_SCHEDULE_URL = "http://jw2012.gdcp.cn/xskbcx.aspx?xh="
            + Constants.LOGIN_BODY_NAME_USERNAME + "&xm=" + Constants.STUDENTNAME + "&gnmkdm=N121603";
    /**
     * 查询成绩的网址
     */
    public static final String SEARCH_SCORE_URL = "http://jw2012.gdcp.cn/xscj_gc.aspx?xh="
            + Constants.LOGIN_BODY_NAME_USERNAME + "&xm=" + Constants.STUDENTNAME + "&gnmkdm=N121603";

    // 请求头
    public static final String HEADER_NAME_HOST = "Host";
    public static final String HEADER_VALUE_HOST = "jw2012.gdcp.cn";
    public static final String HEADER_NAME_REFERER = "Referer";
    //public static final String HEADER_VALUE_REFERER ="http://jw2012.gdcp.cn/xs_main.aspx?xh=1513157140";
    public static final String HEADER_VALUE_REFERER = "http://jw2012.gdcp.cn/xs_main.aspx?xh=";
    public static final String HEADER_NAME_AGENT = "User-Agent";
    public static final String HEADER_VALUE_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0";
    // 登录时的请求参数
    public static final String LOGIN_BODY_NAME_VIEWSTATE = "__VIEWSTATE";
    public static final String LOGIN_BODY_VALUE_VIEWSTATE = "dDw3OTkxMjIwNTU7Oz5mSpILfj7/LApZyRdkN4wQVQlKwg==";
    public static final String LOGIN_BODY_NAME_VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    public static final String LOGIN_BODY_VALUE_VIEWSTATEGENERATOR = "92719903";
    public static final String LOGIN_BODY_NAME_BUTTON1 = "Button1";
    public static final String LOGIN_BODY_VALUE_BUTTON1 = "";
    public static final String LOGIN_BODY_NAME_HIDPDRS = "hidPdrs";
    public static final String LOGIN_BODY_VALUE_HIDPDRS = "";
    public static final String LOGIN_BODY_NAME_HIDSC = "hidsc";
    public static final String LOGIN_BODY_VALUE_HIDSC = "";
    public static final String LOGIN_BODY_NAME_LANGUAGE = "lbLanguage";
    public static final String LOGIN_BODY_VALUE_LANGUAGE = "";
    public static final String LOGIN_BODY_NAME_TYPE = "RadioButtonList1";
    public static final String LOGIN_BODY_VALUE_TYPE = "学生";
    public static final String LOGIN_BODY_NAME_PASSWORD = "TextBox2";
    public static final String LOGIN_BODY_VALUE_PASSWORD = "";
    public static final String LOGIN_BODY_NAME_SECRETCODE = "TextBox3";
    public static final String LOGIN_BODY_VALUE_SECRETCODE = "";
    public static final String LOGIN_BODY_NAME_USERNAME = "TextBox1";
    public static final String LOGIN_BODY_VALUE_USERNAME = "";
    // 查询课表时的请求参数



    public static final String SCHEDULE_BODY_NAME_EVENTARGUMENT = "__EVENTARGUMENT";
    public static final String SCHEDULE_BODY_VALUE_EVENTARGUMENT = "";
    public static final String SCHEDULE_BODY_NAME_EVENTTARGET = "__EVENTTARGET";
    public static final String SCHEDULE_BODY_VALUE_EVENTTARGET = "xqd";
    public static final String SCHEDULE_BODY_NAME_VIEWSTATE = "__VIEWSTATE";
    public static final String SCHEDULE_BODY_VALUE_VIEWSTATE = "\t\n" +
            "dDwtODAxODI2NDQzO3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47PjtsPHQ8O2w8aTwxPjtpPDM+O2k8NT47aTw5Pjs\n" +
            "+O2w8dDw7bDxpPDA+Oz47bDx0PDtsPGk8MD47aTwxPjtpPDM+O2k8Nj47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8XGU7Pj47Pjs7Pjt0PHQ8cDxwPGw8RGF0YVRleHRGaWVsZDtEYXRhVmFsdWVGaWVsZDs\n" +
            "+O2w8eG47eG47Pj47Pjt0PGk8Mz47QDwyMDE2LTIwMTc7MjAxNS0yMDE2O1xlOz47QDwyMDE2LTIwMTc7MjAxNS0yMDE2O1xlOz4\n" +
            "+O2w8aTwwPjs+Pjs7Pjt0PHQ8OztsPGk8MT47Pj47Oz47dDw7bDxpPDA+Oz47bDx0PHQ8cDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs\n" +
            "+Pjs+OztsPGk8MD47Pj47Oz47Pj47Pj47Pj47dDw7bDxpPDA+Oz47bDx0PDtsPGk8MT47aTwzPjtpPDU+O2k8Nz47aTw5Pjs+O2w8dDx0PHA8cDxsPERhdGFUZXh0RmllbGQ7RGF0YVZhbHVlRmllbGQ7PjtsPG5qO25qOz4\n" +
            "+Oz47dDxpPDg+O0A8MjAxNjsyMDE1OzIwMTQ7MjAxMzsyMDEyOzIwMTE7MjAxMDtcZTs+O0A8MjAxNjsyMDE1OzIwMTQ7MjAxMzsyMDEyOzIwMTE7MjAxMDtcZTs\n" +
            "+PjtsPGk8MD47Pj47Oz47dDx0PHA8cDxsPERhdGFUZXh0RmllbGQ7RGF0YVZhbHVlRmllbGQ7PjtsPHh5bWM7eHlkbTs+Pjs+O3Q8aTwxMD47QDzlnJ\n" +
            "/mnKjlt6XnqIvlrabpmaI75rG96L2m5LiO5py65qKw5bel56iL5a2m6ZmiO+i/kOi+k+euoeeQhuWtpumZojvorqHnrpfmnLrlt6XnqIvlrabpmaI76L2o6YGT5Lqk6YCa5a2m6ZmiO\n" +
            "+a1t+S6i+WtpumZojvnlLXlrZDkuI7pgJrkv6Hlt6XnqIvlrabpmaI75ZWG6LS45a2m6ZmiO+acuueUteW3peeoi+WtpumZojtcZTs\n" +
            "+O0A8NTE7NTI7NTM7NTQ7NTU7NTY7NTc7NTg7NjQ7XGU7Pj47bDxpPDA+Oz4+Ozs+O3Q8dDxwPHA8bDxEYXRhVGV4dEZpZWxkO0RhdGFWYWx1ZUZpZWxkOz47bDx6eW1jO3p5ZG07Pj47Pjt0PGk8MjE\n" +
            "+O0A85bu6562R5bel56iL566h55CG77yI6YGT6Lev5qGl5qKB77yJKDLlubTliLYpO+W3peeoi+a1i+mHj+aKgOacrzvln47luILovajpgZPkuqTpgJrlt6XnqIvmioDmnK\n" +
            "/vvIjlronlhajmioDmnK/nrqHnkIbvvIk75Z+O5biC6L2o6YGT5Lqk6YCa5bel56iL5oqA5pyv77yI5pa95bel5rWL6YeP77yJO+W4guaUv\n" +
            "+W3peeoi+aKgOacrygy5bm05Yi2KTvpq5jnrYnnuqflhazot6/nu7TmiqTkuI7nrqHnkIY75bu6562R5bel56iL5oqA5pyvKDLlubTliLYpO\n" +
            "+W7uuetkeW3peeoi+aKgOacryjlu7rnrZHlraYpO+eJqeS4mueuoeeQhjvlu7rnrZHlt6XnqIvnrqHnkIYo6Lev5qGl5pa55ZCRKTvmiL\n" +
            "/lnLDkuqfnu4/okKXkuI7kvLDku7c75Z+O5biC6L2o6YGT5Lqk6YCa5bel56iL5oqA5pyvO+mBk+i3r+ahpeaigeW3peeoi+aKgOacryjlt6XnqIvnm5HnkIbmlrnlkJEpO\n" +
            "+WFrOi3r+S4juahpeaigeW3peeoi+aKgOacrzvluILmlL/lt6XnqIvmioDmnK875bu6562R5bel56iL5oqA5pyvO+W7uuetkeW3peeoi\n" +
            "+aKgOacryjlt6XnqIvnm5HnkIbmlrnlkJEpO+W3peeoi+mAoOS7tzvpgZPot6/moaXmooHlt6XnqIvmioDmnK876YGT6Lev5qGl5\n" +
            "qKB5bel56iL5oqA5pyvO1xlOz47QDwwMTAzOzAxMDg7MDE3NTswMTc2OzAyNjI7MDI3MzswMjU0OzAyNTU7MDExNDswMTEyOzAxMTM7MDE3MTswMTA1OzAxMDY7MDEwNzswMTA5OzAxMTA7MDExMTswMTA0OzAxMDI7XGU7Pj47bDxpPDA\n" +
            "+Oz4+Ozs+O3Q8dDxwPHA8bDxEYXRhVGV4dEZpZWxkO0RhdGFWYWx1ZUZpZWxkOz47bDxiam1jO2JqZG07Pj47Pjt0PGk8MT47QDxcZTs\n" +
            "+O0A8XGU7Pj47bDxpPDA+Oz4+Ozs+O3Q8dDxwPHA8bDxEYXRhVGV4dEZpZWxkO0RhdGFWYWx1ZUZpZWxkOz47bDx4bTt4aDs+Pjs\n" +
            "+O3Q8aTwxPjtAPFxlOz47QDxcZTs+Pjs+Ozs+Oz4+Oz4+O3Q8O2w8aTwwPjs+O2w8dDw7bDxpPDA+O2k8Mj47aTw0PjtpPDY+O2k8OD47aTwxMT47PjtsPHQ8cDxwPGw8VGV4dDs\n" +
            "+O2w85a2m5Y+377yaMTUxMzE1NzE0MDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85aeT5ZCN77ya6LCi5ZiJ5Y2OOz4+Oz47Oz4\n" +
            "7dDxwPHA8bDxUZXh0Oz47bDzlrabpmaLvvJrorqHnrpfmnLrlt6XnqIvlrabpmaI7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOS4k\n" +
            "+S4mu+8mui9r+S7tuaKgOacryjova/ku7blt6XnqIvmlrnlkJEpOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzooYzmlL/nj63vvJoxNei9r\n" +
            "+S7tuaKgOacr++8iDHvvIk7Pj47Pjs7Pjt0PHQ8OztsPGk8MD47Pj47Oz47Pj47Pj47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE\n" +
            "+O2k8MD47aTwwPjtsPD47Pj47Pjs7Ozs7Ozs7Ozs+Ozs+Oz4+O3Q8QDA8cDxwPGw8UGFnZUNvdW50O18hSXRlbUNvdW50O18hRGF0YVNvdXJjZUl0ZW1Db3VudDtEYXRhS2V5czs\n" +
            "+O2w8aTwxPjtpPDI+O2k8Mj47bDw+Oz4+Oz47Ozs7Ozs7Ozs7PjtsPGk8MD47PjtsPHQ8O2w8aTwxPjtpPDI+Oz47bDx0PDtsPGk8MD47aTwxPjtpPDI\n" +
            "+O2k8Mz47aTw0PjtpPDU+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOenu+WKqOW6lOeUqOW8gOWPkeWunuiurTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w85byg5YevOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwxOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwxOS0xOTs+Pjs+Ozs\n" +
            "+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+Oz4+O3Q8O2w8aTwwPjtpPDE\n" +
            "+O2k8Mj47aTwzPjtpPDQ+O2k8NT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8V0VC5qGG5p625bqU55So5a6e6K6tOz4+Oz47Oz47dDx\n" +
            "wPHA8bDxUZXh0Oz47bDzlkajmsZ87Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDE7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDE4LTE4Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47Pj47Pj47Pj47dDxwPGw8VmlzaWJsZTs\n" +
            "+O2w8bzxmPjs+Pjs7Pjt0PHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+O2w8aTwwPjs+O2w8dDw7bDxpPDA+Oz47bDx0PEAwPHA8cDxsPFBhZ2VDb3VudDtfIUl0ZW1Db3VudDtfIURhdGFTb3VyY2VJdGVtQ291bnQ7RGF0YUtleXM7PjtsPGk8MT47aTwwPjtpPDA\n" +
            "+O2w8Pjs+Pjs+Ozs7Ozs7Ozs7Oz47Oz47Pj47Pj47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE\n" +
            "+O2k8MD47aTwwPjtsPD47Pj47Pjs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8cDxwPGw8UGFnZUNvdW50O18hSXRlbUNvdW50O18hRGF0YVNvdXJjZUl0ZW1Db3VudDtEYXRhS2V5czs\n" +
            "+O2w8aTwxPjtpPDQ+O2k8ND47bDw+Oz4+Oz47Ozs7Ozs7Ozs7PjtsPGk8MD47PjtsPHQ8O2w8aTwxPjtpPDI+O2k8Mz47aTw0Pjs\n" +
            "+O2w8dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM+O2k8ND47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8MjAxNi0yMDE3Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwyOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlubjnpo/lv4PnkIblraY7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOael+aYvuaVsDs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MS41Oz4+Oz47Oz47Pj47dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM+O2k8ND47PjtsPHQ8cDxwPGw8VGV4dDs\n" +
            "+O2w8MjAxNi0yMDE3Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwyOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDznp7vliqjlupTnlKjlvIDlj5Hlrp7orq07Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOW8oOWHrzs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MTs+Pjs+Ozs+Oz4+O3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDIwMTYtMjAxNzs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Mjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85Yib5paw5oCd57u06K6t57uDOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzmnpfmmL7mlbA7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDEuNTs\n" +
            "+Pjs+Ozs+Oz4+O3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDIwMTYtMjAxNzs+Pjs\n" +
            "+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Mjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8V0VC5qGG5p625bqU55So5a6e6K6tOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlkajmsZ87Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDE7Pj47Pjs7Pjs\n" +
            "+Pjs+Pjs+Pjs+Pjs+RwKu6sHikXnxmeEKt4Yo9PEfq84=";
    public static final String SCHEDULE_BODY_NAME_VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    public static final String SCHEDULE_BODY_VALUE_VIEWSTATEGENERATOR = "55530A43";
    public static final String SCHEDULE_BODY_NAME_SCHOOLYEAR = "xnd";
    public static final String SCHEDULE_BODY_VALUE_SCHOOLYEAR = "2015-2016";
    public static final String SCHEDULE_BODY_NAME_TERM = "xqd";
    public static final String SCHEDULE_BODY_VALUE_TERM = "1";














    // 查询成绩时的请求参数
    public static final String SCORE_BODY_NAME_VIEWSTATE = "__VIEWSTATE";
    public static final String SCORE_BODY_VALUE_VIEWSTATE = "\t\n" +
            "dDwtMTY2MjI4OTM3Njt0PHA8bDx4aDtzZmRjYms7ZHlieXNjajt6eGNqY3h4cztzdHJfeG54cWN4Oz47bDwxNTEzMTU3MTQwOzA7XGU7MDsxOz4\n" +
            "+O2w8aTwxPjs+O2w8dDw7bDxpPDE+O2k8Mz47aTw1PjtpPDc+O2k8OT47aTwxMT47aTwxMz47aTwxNT47aTwyND47aTwyNT47aTwyNj47aTwzMz47aTwzNT47aTwzNz47aTwzOT47aTw0MT47aTw0Mz47PjtsPHQ8cDxwPGw8VGV4dDs\n" +
            "+O2w85a2m5Y+377yaMTUxMzE1NzE0MDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85aeT5ZCN77ya6LCi5ZiJ5Y2OOz4+Oz47Oz4\n" +
            "7dDxwPHA8bDxUZXh0Oz47bDzlrabpmaLvvJrorqHnrpfmnLrlt6XnqIvlrabpmaI7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOS4k\n" +
            "+S4mu+8mjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w86L2v5Lu25oqA5pyvKOi9r+S7tuW3peeoi+aWueWQkSk7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOihjOaUv\n" +
            "+ePre+8mjE16L2v5Lu25oqA5pyv77yIMe+8iTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MjAxNTAxNTc7Pj47Pjs7Pjt0PHQ8O\n" +
            "3Q8aTwxOD47QDxcZTsyMDAxLTIwMDI7MjAwMi0yMDAzOzIwMDMtMjAwNDsyMDA0LTIwMDU7MjAwNS0yMDA2OzIwMDYtMjAwNzsyM\n" +
            "DA3LTIwMDg7MjAwOC0yMDA5OzIwMDktMjAxMDsyMDEwLTIwMTE7MjAxMS0yMDEyOzIwMTItMjAxMzsyMDEzLTIwMTQ7MjAxNC0yM\n" +
            "DE1OzIwMTUtMjAxNjsyMDE2LTIwMTc7MjAxNy0yMDE4Oz47QDxcZTsyMDAxLTIwMDI7MjAwMi0yMDAzOzIwMDMtMjAwNDsyMDA0L\n" +
            "TIwMDU7MjAwNS0yMDA2OzIwMDYtMjAwNzsyMDA3LTIwMDg7MjAwOC0yMDA5OzIwMDktMjAxMDsyMDEwLTIwMTE7MjAxMS0yMDEyOzIwMTItMjAxMzsyMDEzLTIwMTQ7MjAxNC0yMDE1OzIwMTUtMjAxNjsyMDE2LTIwMTc7MjAxNy0yMDE4Oz4\n" +
            "+Oz47Oz47dDxwPDtwPGw8b25jbGljazs+O2w8d2luZG93LnByaW50KClcOzs+Pj47Oz47dDxwPDtwPGw8b25jbGljazs+O2w8d2luZG93LmNsb3NlKClcOzs\n" +
            "+Pj47Oz47dDxwPHA8bDxWaXNpYmxlOz47bDxvPHQ+Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwyMDE1LTIwMTblrablubTnrKwx5a2m5pyf5a2m5Lmg5oiQ57upOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzmiYDpgInlrabliIYyMS41MO+8m+iOt+W+l+WtpuWIhjIxLjUw77yb6YeN5L+u5a2m5YiG44CCOz4\n" +
            "+Oz47Oz47dDw7bDxpPDA+O2k8MT47aTwzPjtpPDU+O2k8Nz47aTw5PjtpPDExPjtpPDEzPjtpPDE1PjtpPDE3PjtpPDE5PjtpPDIxPjtpPDIyPjs\n" +
            "+O2w8dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE\n" +
            "+O2k8OD47aTw4PjtsPD47Pj47PjtAMDw7Ozs7Ozs7QDA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Ozs7PjtAMDxwPGw8VmlzaWJsZTs\n" +
            "+O2w8bzx0Pjs+Pjs7Ozs+O0AwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+Ozs7Oz47QDA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Ozs7PjtAMDxwPGw8VmlzaWJsZTs\n" +
            "+O2w8bzxmPjs+Pjs7Ozs+O0AwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+Ozs7Oz47Ozs7QDA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Ozs7PjtAMDxwPGw8VmlzaWJsZTs\n" +
            "+O2w8bzx0Pjs+Pjs7Ozs+Ozs7Oz47Ozs7Ozs7Ozs+O2w8aTwwPjs+O2w8dDw7bDxpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47aTw2PjtpPDc\n" +
            "+O2k8OD47PjtsPHQ8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47aTw2PjtpPDc+O2k8OD47aTw5PjtpPDEwPjtpPDExPjtpPDEyPjtpPDEzPjtpPDE0PjtpPDE1PjtpPDE2PjtpPDE3PjtpPDE4PjtpPDE5PjtpPDIwPjs\n" +
            "+O2w8dDxwPHA8bDxUZXh0Oz47bDwyMDE1LTIwMTY7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDE7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDEzNTAwMzJCOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDxvZmZpY2Xpq5jmlYjlip7lhaw7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOW/heS/ruivvjs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8My41Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwzLjM7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDx\n" +
            "sPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDgzOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47Pj47dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM+O2k8ND47aTw1PjtpPDY\n" +
            "+O2k8Nz47aTw4PjtpPDk+O2k8MTA+O2k8MTE+O2k8MTI+O2k8MTM+O2k8MTQ+O2k8MTU+O2k8MTY+O2k8MTc+O2k8MTg+O2k8MTk\n" +
            "+O2k8MjA+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDIwMTUtMjAxNjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8MTA0MDA2MEI7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOWkp+WtpuiLseivrTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85b\n" +
            "+F5L+u6K++Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwzLjU7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDEuOTs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Njk7Pj4\n" +
            "7Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDA7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFR\n" +
            "leHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZ\n" +
            "uYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDA7Pj47Pjs7Pjs\n" +
            "+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+O2k8Nj47aTw3PjtpPDg+O2k8OT47aTwxMD47aTwxMT47aTwxMj47aTwxMz47aTwxND47aTwxNT47aTwxNj47aTwxNz47aTwxOD47aTwxOT47aTwyMD47PjtsPHQ8cDxwPGw8VGV4dDs\n" +
            "+O2w8MjAxNS0yMDE2Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwxOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwxMzUwMDMzQjs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w86K6h566X5py65LiO572R57uc5Z+656GAOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlv4Xkv67or747Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDMuNTs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8My41Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDw4NTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8MDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47aTw2PjtpPDc+O2k8OD47aTw5PjtpPDEwPjtpPDExPjtpPDEyPjtpPDEzPjtpPDE0PjtpPDE1PjtpPDE2PjtpPDE3PjtpPDE4PjtpPDE5PjtpPDIwPjs\n" +
            "+O2w8dDxwPHA8bDxUZXh0Oz47bDwyMDE1LTIwMTY7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDE7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDAwMDAwMTBCOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlhpvorq3kuI7lhaXlrabmlZnogrI7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOW/heS\n" +
            "/ruivvjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Mjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8My41Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDw4NTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8MDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47aTw2PjtpPDc+O2k8OD47aTw5PjtpPDEwPjtpPDExPjtpPDEyPjtpPDEzPjtpPDE0PjtpPDE1PjtpPDE2PjtpPDE3PjtpPDE4PjtpPDE5PjtpPDIwPjs\n" +
            "+O2w8dDxwPHA8bDxUZXh0Oz47bDwyMDE1LTIwMTY7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDE7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDAwMDE7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOevrueQgzs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85b+F5L+u6K++Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz4\n" +
            "7dDxwPHA8bDxUZXh0Oz47bDwxLjU7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDIuNTs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8NzU7Pj4\n" +
            "7Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDA7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFR\n" +
            "leHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZ\n" +
            "uYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDA7Pj47Pjs7Pjs\n" +
            "+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+O2k8Nj47aTw3PjtpPDg+O2k8OT47aTwxMD47aTwxMT47aTwxMj47aTwxMz47aTwxND47aTwxNT47aTwxNj47aTwxNz47aTwxOD47aTwxOT47aTwyMD47PjtsPHQ8cDxwPGw8VGV4dDs\n" +
            "+O2w8MjAxNS0yMDE2Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwxOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwMDAwMDE1Qjs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85oCd5oOz6YGT5b635L+u5YW75LiO5rOV5b6L5Z+656GAOz4+Oz47Oz47dDxwPHA8bDx\n" +
            "UZXh0Oz47bDzlv4Xkv67or747Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPCZuYnNwXDs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDEuNTs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8My4xOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDw4MTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8MDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47aTw2PjtpPDc+O2k8OD47aTw5PjtpPDEwPjtpPDExPjtpPDEyPjtpPDEzPjtpPDE0PjtpPDE1PjtpPDE2PjtpPDE3PjtpPDE4PjtpPDE5PjtpPDIwPjs\n" +
            "+O2w8dDxwPHA8bDxUZXh0Oz47bDwyMDE1LTIwMTY7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDE7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDE1NzAwMzVCOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDznvZHpobXorr7orqHkuI7lm77lg4/lpITnkIY7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOW\n" +
            "/heS/ruivvjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8NTs+Pjs+Ozs\n" +
            "+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8My44Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDw4ODs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8MDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47aTw2PjtpPDc+O2k8OD47aTw5PjtpPDEwPjtpPDExPjtpPDEyPjtpPDEzPjtpPDE0PjtpPDE1PjtpPDE2PjtpPDE3PjtpPDE4PjtpPDE5PjtpPDIwPjs\n" +
            "+O2w8dDxwPHA8bDxUZXh0Oz47bDwyMDE1LTIwMTY7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDE7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDAwMDAwMTdCOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlvaLlir/kuI7mlL/nrZY7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOW/heS/ruivvjs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MS41Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDw2NTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8MDs+Pjs+Ozs+Oz4+Oz4+Oz4+O3Q8QDA8cDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs+Pjs+Ozs7Ozs7Ozs7Oz47Oz47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE\n" +
            "+O2k8ND47aTw0PjtsPD47Pj47Pjs7Ozs7Ozs7Ozs+O2w8aTwwPjs+O2w8dDw7bDxpPDE+O2k8Mj47aTwzPjtpPDQ+Oz47bDx0PDtsPGk8MD47aTwxPjtpPDI\n" +
            "+O2k8Mz47aTw0PjtpPDU+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDAxOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlv4Xkv67or747Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDA7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDIxLjUwOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47Pj47dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM\n" +
            "+O2k8ND47aTw1Pjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwwMjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w86ZmQ6YCJ6K++Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4\n" +
            "+Oz47Oz47Pj47dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM+O2k8ND47aTw1Pjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwwNDs+Pjs+Ozs\n" +
            "+O3Q8cDxwPGw8VGV4dDs+O2w85YWs5YWx6YCJ5L+u6K++Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47Pj47dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM\n" +
            "+O2k8ND47aTw1Pjs+O2w8dDxwPHA8bDxUZXh0Oz47bDw5OTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8XDxiXD7lkIjorqFcPC9iXD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDA7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDIxLjUwOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47Pj47Pj47Pj47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE\n" +
            "+O2k8Mj47aTwyPjtsPD47Pj47Pjs7Ozs7Ozs7Ozs+O2w8aTwwPjs+O2w8dDw7bDxpPDE+O2k8Mj47PjtsPHQ8O2w8aTwwPjtpPDE\n" +
            "+O2k8Mj47aTwzPjtpPDQ+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOaJgOacieexu+WIqzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MDs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8MDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8MDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPFw8Ylw+5ZCI6K6hXDwvYlw\n" +
            "+Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwOz4+Oz47Oz47Pj47Pj47Pj47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE\n" +
            "+O2k8MT47aTwxPjtsPD47Pj47Pjs7Ozs7Ozs7Ozs+O2w8aTwwPjs+O2w8dDw7bDxpPDE+Oz47bDx0PDtsPGk8MD47aTwxPjtpPDI\n" +
            "+O2k8Mz47aTw0PjtpPDU+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOWQiOiuoTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs\n" +
            "+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85qyhOz4+Oz47Oz47Pj47Pj47Pj47dDxAMDxwPHA8bDxWaXNpYmxlO1BhZ2VDb3VudDtfIUl0ZW1Db3VudDtfIURhdGFTb3VyY2VJdGVtQ291bnQ7RGF0YUtleXM7PjtsPG88Zj47aTwxPjtpPDA\n" +
            "+O2k8MD47bDw+Oz4+Oz47Ozs7Ozs7Ozs7Pjs7Pjt0PEAwPHA8cDxsPFZpc2libGU7UGFnZUNvdW50O18hSXRlbUNvdW50O18hRGF0YVNvdXJjZUl0ZW1Db3VudDtEYXRhS2V5czs\n" +
            "+O2w8bzxmPjtpPDE+O2k8MD47aTwwPjtsPD47Pj47Pjs7Ozs7Ozs7Ozs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85pys5LiT5Lia5YWxMTkx5Lq6Oz4\n" +
            "+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlubPlnYflrabliIbnu6nngrnvvJozLjA5Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlrabliIbnu6nngrnmgLvlkozvvJo2Ni4zNTs\n" +
            "+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8XGU7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPHpmOz4+Oz47Oz47dDxwPHA8bDxJbWFnZVVybDs\n" +
            "+O2w8Li9leGNlbC85NzA1MjcwLmpwZzs+Pjs+Ozs+Oz4+O3Q8cDxwPGw8VGV4dDtWaXNpYmxlOz47bDzoh7Pku4rmnKrpgJrov4for77nqIvvvJo7bzx0Pjs\n" +
            "+Pjs+Ozs+O3Q8QDA8cDxwPGw8UGFnZUNvdW50O18hSXRlbUNvdW50O18hRGF0YVNvdXJjZUl0ZW1Db3VudDtEYXRhS2V5czs+O2w8aTwxPjtpPDA\n" +
            "+O2k8MD47bDw+Oz4+Oz47QDA8QDA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Ozs7PjtAMDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs\n" +
            "+Pjs7Ozs+Ozs7Ozs7Oz47Ozs7Ozs7Ozs+Ozs+O3Q8QDA8Ozs7Ozs7Ozs7Oz47Oz47Pj47Pj47PnQ9weDV+4KWMK1trgC00xXYmCu\n" +
            "P";
    public static final String SCORE_BODY_NAME_VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    public static final String SCORE_BODY_VALUE_VIEWSTATEGENERATOR = "DB0F94E3";
    public static final String SCORE_BODY_NAME_BUTTON1 = "Button1";
    public static final String SCORE_BODY_VALUE_BUTTON1 = "按学期查询";
    public static final String SCORE_BODY_NAME_SCHOOLYEAR = "ddlXN";
    public static final String SCORE_BODY_VALUE_SCHOOLYEAR = "2015-2016";
    public static final String SCORE_BODY_NAME_TERM = "ddlXQ";
    public static final String SCORE_BODY_VALUE_TERM = "1";







    public static final String SCORE_BODY_NAME_TXTQSCJ = "txtQSCJ";
    public static final String SCORE_BODY_VALUE_TXTQSCJ = "0";
    public static final String SCORE_BODY_NAME_TXTZZCJ = "txtZZCJ";
    public static final String SCORE_BODY_VALUE_TXTZZCJ = "100";

}
