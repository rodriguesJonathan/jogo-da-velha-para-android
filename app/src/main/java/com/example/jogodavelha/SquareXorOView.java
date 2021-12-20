package com.example.jogodavelha;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

public class SquareXorOView extends androidx.appcompat.widget.AppCompatImageView {
    private DisplayMetrics displayMetrics;
    private char letraAtual;
    public static byte vezesPreenchida = 0;

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
