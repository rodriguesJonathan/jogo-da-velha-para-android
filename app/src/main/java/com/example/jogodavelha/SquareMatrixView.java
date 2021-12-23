package com.example.jogodavelha;

import android.content.Context;
import android.util.AttributeSet;

public class SquareMatrixView extends androidx.appcompat.widget.AppCompatImageView {

    public SquareMatrixView(Context context) {
        super(context);
    }

    public SquareMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareMatrixView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

}