package com.dehe.android_midtermproj;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by lenovo on 2017/11/19.
 */

public class NewListView extends ListView {
    public NewListView(Context context) {
        super(context);
    }

    public NewListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec-180);
    }
}
