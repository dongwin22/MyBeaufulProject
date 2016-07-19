package com.example.dllo.mybeaufulproject.tool;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by wubihang on 16/7/13.
 * 自定义的Listview
 */
public class GuideNoListView extends ListView {
    public GuideNoListView(Context context) {
        super(context);
    }

    public GuideNoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GuideNoListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 重新测量 -规定他的高度是展开的
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true; //禁止ListView滑动
        }
        return super.dispatchTouchEvent(ev);
    }

}
