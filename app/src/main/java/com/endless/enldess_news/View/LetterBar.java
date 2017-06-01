package com.endless.enldess_news.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/26.
 */

public class LetterBar extends View {
    /**
     * 索引字母颜色
     */
    private static final int LETTER_COLOR = 0xff595959;

    /**
     * 字母索引条背景颜色
     */
    private static final int BG_COLOR = 0xffB0B0B0;

    /**
     * 26个字母
     */
    public static final String[] LETTERS = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    private Paint paint;

    private float width;
    private float height;

    /**
     * 单元格的高度
     */
    private float cellHeight;
    private TextView tvLetter;


    public LetterBar(Context context) {
        super(context);
        init();
    }

    public LetterBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setTextSize(dp2px(12));
        paint.setColor(LETTER_COLOR);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        cellHeight = height / LETTERS.length;
    }

    public int dp2px(int dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    private float getTextWidth(String text) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.width();
    }

    private float getTextHeight(String text) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0,text.length(),  rect);
        return rect.height();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];
            float x = width/2-getTextWidth(letter)/2;
            float y =cellHeight/2+ getTextHeight(letter)/2+cellHeight*i;
            canvas.drawText(letter, x, y, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = (int) (event.getY() / cellHeight);
        String letter =  LETTERS[index];
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    listener.select(index, letter);
                    tvLetter.setText(letter);
                    tvLetter.setVisibility(VISIBLE);
                    break;
                case MotionEvent.ACTION_MOVE:
                    listener.select(index, letter);
                    tvLetter.setText(letter);
                    tvLetter.setVisibility(VISIBLE);
                    break;
                case MotionEvent.ACTION_UP:
                    tvLetter.setVisibility(GONE);
                    break;


            }
            return super.onTouchEvent(event);

    }
    private OnLetterSelectListener listener;
    public void setOnLetterSelectListener(OnLetterSelectListener listener){
        this.listener=listener;
    }
    public interface OnLetterSelectListener{
        void select(int index, String letter);
    }
    public void setLetterTextView(TextView tvLetter) {
        this.tvLetter = tvLetter;
    }
}
