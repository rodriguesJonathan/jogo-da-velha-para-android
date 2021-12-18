package com.example.jogodavelha;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

public class SquareXorOView extends androidx.appcompat.widget.AppCompatImageView {
    DisplayMetrics displayMetrics;

    public SquareXorOView(Context context) {
        super(context);
        displayMetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    public SquareXorOView(Context context, AttributeSet attrs) {
        super(context, attrs);
        displayMetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    public SquareXorOView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        displayMetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int screenWidth = displayMetrics.widthPixels;
        int width = (int) (screenWidth*0.2);

        setMeasuredDimension(width, width);
    }
}
