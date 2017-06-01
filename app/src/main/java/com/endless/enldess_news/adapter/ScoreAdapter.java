package com.endless.enldess_news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.endless.enldess_news.R;
import com.endless.enldess_news.bean.Score;

import java.util.List;

/**
 * 成绩ListView的适配器类
 */
public class ScoreAdapter extends BaseAdapter {
    private Context context;
    private List<Score> scoreList;

    public ScoreAdapter(Context context, List<Score> scoreList) {
        this.context = context;
        this.scoreList = scoreList;
    }

    @Override
    public int getCount() {
        return scoreList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return scoreList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        Score score = (Score) getItem(arg0);
        View view;
        ViewHolder viewHolder;
        if(arg1 == null) {
            view = LayoutInflater.from(context).inflate(R.layout.score_item, null);
            viewHolder = new ViewHolder();
            viewHolder.subject = (TextView) view.findViewById(R.id.tv_subject);
            viewHolder.category = (TextView) view.findViewById(R.id.tv_category);
            viewHolder.credit = (TextView) view.findViewById(R.id.tv_credit);
            viewHolder.gradePoint = (TextView) view.findViewById(R.id.tv_gradePoint);
            viewHolder.score = (TextView) view.findViewById(R.id.tv_score);
            viewHolder.finalScore = (TextView) view.findViewById(R.id.tv_finalScore);
            view.setTag(viewHolder);
        } else {
            view = arg1;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.subject.setText(score.getSubject());
        viewHolder.category.setText(score.getCategory());
        viewHolder.credit.setText(score.getCredit());
        viewHolder.gradePoint.setText(score.getGradePoint());
        viewHolder.score.setText(score.getScore());
        viewHolder.finalScore.setText(score.getFinalScore());
        return view;
    }

    class ViewHolder {
        TextView subject;
        TextView category;
        TextView credit;
        TextView gradePoint;
        TextView score;
        TextView finalScore;
    }
}
