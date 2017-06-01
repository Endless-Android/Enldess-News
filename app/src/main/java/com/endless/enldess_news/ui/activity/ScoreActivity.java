package com.endless.enldess_news.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;

import com.endless.enldess_news.R;
import com.endless.enldess_news.adapter.ScoreAdapter;
import com.endless.enldess_news.bean.Score;

import java.util.ArrayList;
import java.util.List;


public class ScoreActivity extends AppCompatActivity {

    private ArrayList<Score> scoreList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_score);
        TextView text = (TextView) findViewById(R.id.ca_title_text);
        text.setText("大广交成绩单");
        init();
        initAdapter();
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
     * 通过调用这个方法启动ScoreActivity
     */
    public static void actionStart(Context context, List<Score> scoreList) {
        Intent intent = new Intent(context, ScoreActivity.class);
        intent.putParcelableArrayListExtra("scoreList", (ArrayList<Score>) scoreList);
        context.startActivity(intent);
    }

    public void init() {
        Intent intent = ScoreActivity.this.getIntent();
        scoreList = intent.getParcelableArrayListExtra("scoreList");

        listView = (ListView) ScoreActivity.this.findViewById(R.id.score_listview);
    }

    private void initAdapter() {
        ScoreAdapter adapter = new ScoreAdapter(this, scoreList);
        listView.setAdapter(adapter);
    }
}
