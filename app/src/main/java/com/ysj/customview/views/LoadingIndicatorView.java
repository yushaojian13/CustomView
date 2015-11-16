package com.ysj.customview.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.ysj.customview.R;
import com.ysj.customview.views.indicators.BallPulseIndicator;
import com.ysj.customview.views.indicators.BaseIndicatorController;
import com.ysj.log.L;

/**
 * Created by yushaojian on 2015 11 15.
 */
public class LoadingIndicatorView extends View {
    // indicators
    public static final int BallPulse = 0;
    public static final int BallGridPulse = 1;
    public static final int BallClipRotate = 2;
    public static final int BallClipRotatePulse = 3;
    public static final int SquareSpin = 4;
    public static final int BallClipRotateMultiple = 5;
    public static final int BallPulseRise = 6;
    public static final int BallRotate = 7;
    public static final int CubeTransition = 8;
    public static final int BallZigZag = 9;
    public static final int BallZigZagDeflect = 10;
    public static final int BallTrianglePath = 11;
    public static final int BallScale = 12;
    public static final int LineScale = 13;
    public static final int LineScaleParty = 14;
    public static final int BallScaleMultiple = 15;
    public static final int BallPulseSync = 16;
    public static final int BallBeat = 17;
    public static final int LineScalePulseOut = 18;
    public static final int LineScalePulseOutRapid = 19;
    public static final int BallScaleRipple = 20;
    public static final int BallScaleRippleMultiple = 21;
    public static final int BallSpinFadeLoader = 22;
    public static final int LineSpinFadeLoader = 23;
    public static final int TriangleSkewSpin = 24;
    public static final int Pacman = 25;
    public static final int BallGridBeat = 26;
    public static final int SemiCircleSpin = 27;

    // Sizes (with defaults in DP)
    public static final int DEFAULT_SIZE = 45;

    private int indicator;
    private int indicatorColor;

    private Paint paint;

    private static final int DEFAULT_COLOR = Color.WHITE;

    private boolean hasAnimation;

    private BaseIndicatorController controller;

    public LoadingIndicatorView(Context context) {
        super(context);
        init(null);
    }

    public LoadingIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingIndicatorView);
        indicator = a.getInt(R.styleable.LoadingIndicatorView_indicator, BallPulse);
        indicatorColor = a.getColor(R.styleable.LoadingIndicatorView_indicator_color, DEFAULT_COLOR);
        a.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(indicatorColor);
        paint.setStyle(Paint.Style.FILL);

        createController();
    }

    private void createController() {
        switch (indicator) {
            case BallPulse:
            default:
                controller = new BallPulseIndicator(this);
        }
    }

    public int getIndicatorColor() {
        return indicatorColor;
    }

    // To provide dynamic behavior
    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        paint.setColor(indicatorColor);
        invalidate();
        L.d(indicatorColor);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!hasAnimation) {
            hasAnimation = true;

            controller.createAnimation();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        controller.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        controller.draw(canvas, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(dp2px(DEFAULT_SIZE), widthMeasureSpec);
        int height = measureDimension(dp2px(DEFAULT_SIZE), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }

        return result;
    }

    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }
}
