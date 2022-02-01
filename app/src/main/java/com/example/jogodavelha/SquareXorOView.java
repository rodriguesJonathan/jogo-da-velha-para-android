package com.example.jogodavelha;

import android.content.Context;
import android.util.AttributeSet;

public class SquareXorOView extends androidx.appcompat.widget.AppCompatImageView {
    private char letraAtual  = '_';

    public SquareXorOView(Context context) {
        super(context);
    }

    public SquareXorOView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareXorOView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public char getLetraAtual() {
        return letraAtual;
    }

    public void setLetraAtual(char letraAtual) {
        this.letraAtual = letraAtual;
    }


}
