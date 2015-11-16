package com.ysj.customview.views.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by yushaojian on 2015 11 16.
 */
public class BallPulseIndicator extends BaseIndicatorController {
    public static final float SCALE = 1.0f;
    private static final float CIRCLE_SPACE = 4;

    private float[] scaleFloats = new float[]{SCALE, SCALE, SCALE};
    private float circleX;
    private float circleY;
    private float circleRadius;

    public BallPulseIndicator(View targetView) {
        super(targetView);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        circleRadius = Math.min((w - CIRCLE_SPACE * 2) / 6, (h - CIRCLE_SPACE * 2) / 2);
        circleX = w * 0.5f - circleRadius * 2 - CIRCLE_SPACE;
        circleY = h * 0.5f;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (canvas == null || paint == null) {
            return;
        }

        for (int i = 0; i < scaleFloats.length; i++) {
            canvas.save();
            canvas.translate(circleX + circleRadius * 2 * i + CIRCLE_SPACE * i, circleY);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            canvas.drawCircle(0, 0, circleRadius, paint);
            canvas.restore();
        }
    }

    @Override
    public void createAnimation() {
        if (targetView == null) {
            return;
        }

        int[] delays = new int[] {120, 240, 360};
        for (int i = 0; i < delays.length; i++) {
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.3f, 1);
            scaleAnim.setDuration(750);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);

            final int index = i;
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });

            scaleAnim.start();
        }
    }
}
