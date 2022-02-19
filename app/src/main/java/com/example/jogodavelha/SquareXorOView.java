package com.example.jogodavelha;

import android.content.Context;
import android.util.AttributeSet;

public class SquareXorOView extends androidx.appcompat.widget.AppCompatImageView {
    private String letraAtual  = "_";

    public SquareXorOView(Context context) {
        super(context);
    }

    public SquareXorOView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareXorOView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public String getLetraAtual() {
        return letraAtual;
    }

    public void setLetraAtual(String letraAtual) {
        this.letraAtual = letraAtual;
    }


}
