package com.endless.enldess_news.bean;

/**
 * Created by Administrator on 2017/5/25.
 */

public class Course {

    /**
     * 课程id
     */
    private String cId;
    /**
     * 课程名
     */
    private String cName;
    /**
     * 课程属性
     */
    private String cState;
    /**
     * 学分
     */
    private String credit;
    /**
     * 绩点
     */
    private String gpa;
    /**
     * 分数
     */
    private String score;
    /**
     * 学年
     */
    private String cYear;
    /**
     * 学期
     */
    private String cTerm;
    /**
     * 开课学院
     */
    private String college;


    public Course() {
    }

    public Course(String cId, String cName, String college, String credit, String cState, String cTerm, String cYear, String gpa, String score) {
        this.cId = cId;
        this.cName = cName;
        this.college = college;
        this.credit = credit;
        this.cState = cState;
        this.cTerm = cTerm;
        this.cYear = cYear;
        this.gpa = gpa;
        this.score = score;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getcState() {
        return cState;
    }

    public void setcState(String cState) {
        this.cState = cState;
    }

    public String getcTerm() {
        return cTerm;
    }

    public void setcTerm(String cTerm) {
        this.cTerm = cTerm;
    }

    public String getcYear() {
        return cYear;
    }

    public void setcYear(String cYear) {
        this.cYear = cYear;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
