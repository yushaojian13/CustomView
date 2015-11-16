package com.ysj.customview.views.indicators;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by yushaojian on 2015 11 16.
 */
public abstract class BaseIndicatorController {
    protected View targetView;

    public BaseIndicatorController(View targetView) {
        this.targetView = targetView;
    }

    public int getWidth() {
        if (targetView == null) {
            return 0;
        }

        return targetView.getWidth();
    }

    public int getHeight() {
        if (targetView == null) {
            return 0;
        }

        return targetView.getHeight();
    }

    public abstract void onSizeChanged(int w, int h, int oldw, int oldh);

    public void postInvalidate() {
        if (targetView == null) {
            return;
        }

        targetView.postInvalidate();
    }

    public abstract void draw(Canvas canvas, Paint paint);

    public abstract void createAnimation();
}
