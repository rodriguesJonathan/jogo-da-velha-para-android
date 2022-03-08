package com.example.jogodavelha;

import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.DrawableRes;

public class SquareXorOView extends androidx.appcompat.widget.AppCompatImageView {
    private UiModeManager uiModeManager;

    public SquareXorOView(Context context) {
        super(context);
        uiModeManager = (UiModeManager) context.getApplicationContext().getSystemService(Context.UI_MODE_SERVICE);
        if(uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES){
            this.setColorFilter(Color.WHITE);
        }
    }

    public SquareXorOView(Context context, AttributeSet attrs) {
        super(context, attrs);
        uiModeManager = (UiModeManager) context.getApplicationContext().getSystemService(Context.UI_MODE_SERVICE);
        if(uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES){
            this.setColorFilter(Color.WHITE);
        }
    }

    public SquareXorOView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        uiModeManager = (UiModeManager) context.getApplicationContext().getSystemService(Context.UI_MODE_SERVICE);
        if(uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES){
            this.setColorFilter(Color.WHITE);
        }
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        Animation animationZoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        this.startAnimation(animationZoomIn);

    }


}
