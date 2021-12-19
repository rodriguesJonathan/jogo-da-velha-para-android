package com.example.jogodavelha;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class SquareXorOView extends androidx.appcompat.widget.AppCompatImageView implements Drawable.Callback {
    private DisplayMetrics displayMetrics;
    private char letraAtual;

    public SquareXorOView(Context context) {
        super(context);
        displayMetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.letraAtual = '_';
    }

    public SquareXorOView(Context context, AttributeSet attrs) {
        super(context, attrs);
        displayMetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.letraAtual = '_';
    }

    public SquareXorOView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        displayMetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.letraAtual = '_';
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int screenWidth = displayMetrics.widthPixels;
        int width = (int) (screenWidth*0.2);

        setMeasuredDimension(width, width);
    }



    public char getLetraAtual() {
        return letraAtual;
    }

    public void setLetraAtual(char letraAtual) {
        this.letraAtual = letraAtual;
    }

}
