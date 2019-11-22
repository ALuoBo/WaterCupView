package com.will.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * @author Will
 * @description: waveView
 * @date : 2019/11/20 13:39
 */
public class WaterCupView extends View {
    private float baseLineHeight = 400;
    private Paint paint, paintShadow;
    private Path path, shadowPath;
    private int fillColor;
    private String shadowColor;

    private int startPoint;
    private int waterWidth = 500;
    private int waterHeight;
    private int viewWidth;
    private int viewHeight;


    public WaterCupView(Context context) {
        super(context);
        init(null, 0);
    }

    public WaterCupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public WaterCupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
        invalidate();
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.WaterCupView, defStyle, 0);
        fillColor = a.getColor(R.styleable.WaterCupView_fillColor, Color.parseColor("#2983bb"));
        a.recycle();
        {
            //构造代码块相当于放在构造方法头部
            //外层
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(fillColor);
            paint.setStyle(Paint.Style.FILL);

            //阴影
            paintShadow = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintShadow.setColor(Color.parseColor("#7a7374"));
            paintShadow.setStyle(Paint.Style.FILL);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        setMeasuredDimension(viewWidth, viewHeight);
    }

    {
        path = new Path();
        shadowPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();
        shadowPath.reset();

        path.moveTo(startPoint - 1000, 700);
        for (int i = -waterWidth; i < viewWidth + waterWidth; i = i + waterWidth) {
            path.rCubicTo(waterWidth / 2, 100, waterWidth / 2, -100, waterWidth, 0);
        }shadowPath.moveTo(startPoint , 700);
        for (int i = -waterWidth; i < viewWidth + waterWidth; i = i + waterWidth) {
            shadowPath.rCubicTo(waterWidth / 2, 100, waterWidth / 2, -100, waterWidth, 0);
        }
        path.lineTo(viewWidth, viewHeight);
        path.lineTo(0, viewHeight);
        path.close();
        shadowPath.lineTo(viewWidth, viewHeight);
        shadowPath.lineTo(0, viewHeight);
        shadowPath.close();
        canvas.drawPath(path, paint);
        canvas.drawPath(shadowPath, paintShadow);
    }

    public void startAnimater() {
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "startPoint", 1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

}
