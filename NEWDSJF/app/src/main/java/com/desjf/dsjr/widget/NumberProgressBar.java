package com.desjf.dsjr.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class NumberProgressBar extends ProgressBar {
    TextPaint mPaint;
    Paint mTabPaint;
    float mTextHeight;
    float mTextWidth;


    public NumberProgressBar(Context context) {
        super(context);
        init();
    }

    public NumberProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumberProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public NumberProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setFieldValue("mOnlyIndeterminate", new Boolean(false));
        setIndeterminate(false);
//        setProgressDrawable(getResources().getDrawable(android.R.drawable.progress_horizontal));
//        setIndeterminateDrawable(getResources().getDrawable(android.R.drawable.progress_indeterminate_horizontal));
        setMinimumHeight(160);


        //文本背景
        mTabPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTabPaint.setColor(Color.RED);
        //画进度百分比文本
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(getContext().getResources().getDisplayMetrics().density * 10);
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        mTextHeight = metrics.bottom - metrics.top;
        mTextWidth = mPaint.measureText("88%");
        setPadding(0, 0, (int) (mTextWidth / 2 + 2), 0);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int resetHeight = MeasureSpec.makeMeasureSpec((int) (6 * mTextHeight), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, resetHeight);
        setTranslationY(-mTextHeight * 1);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (100 == getProgress()) {
            mPaint.setTextSize(getContext().getResources().getDisplayMetrics().density * 8);
        } else {
            mPaint.setTextSize(getContext().getResources().getDisplayMetrics().density * 10);
        }
        float radius = mTextWidth / 2 + 2;                                                                        //水滴圆半径
        float textCenterX = (getMeasuredWidth() - radius) * getProgress() / getMax();                             //文本中心X
        float textLeft = (getMeasuredWidth() - radius) * getProgress() / getMax() - mTextWidth / 2;               //文本X
        float baselineY = getMeasuredHeight() / 2 + 2 * mTextHeight + mPaint.getFontMetrics().top;                //文本基线Y
        float circleY = baselineY + (mPaint.getFontMetrics().bottom + mPaint.getFontMetrics().top) / 2;           //圆心Y
        //画水滴文本圆
        canvas.drawCircle(textCenterX, circleY + mTextWidth / 2, radius, mTabPaint);
        //画水滴圆切边三角形
        Path path = new Path();
        double tangle = Math.acos(radius / mTextWidth);
        float rightX = (float) (textCenterX + Math.sin(tangle) * radius);
        float rightY = (float) (circleY + mTextWidth / 2 - Math.cos(tangle) * radius);
        float leftX = (float) (textCenterX - Math.sin(tangle) * radius);
        float leftY = (float) (circleY + mTextWidth / 2 - Math.cos(tangle) * radius);
        path.moveTo(textCenterX, circleY - mTextWidth / 2);
        path.lineTo(rightX, rightY);
        path.lineTo(leftX, leftY);
        path.close();
        canvas.drawPath(path, mTabPaint);
        //画文本
        String text = String.format("%02d%%", getProgress());
        canvas.drawText(text, textLeft, baselineY + mTextWidth / 2, mPaint);
    }

    /**
     * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
     */

    public void setFieldValue(final String fieldName, final Object value) {
        Field field = getDeclaredField(getClass().getSuperclass(), fieldName);
        if (field == null)
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + this + "]");
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {//强制转换fileld可访问.
            field.setAccessible(true);
        }
        try {
            field.set(this, value);
        } catch (IllegalAccessException e) {
            Log.e("zbkc", "", e);
        }
    }

    /**
     * 循环向上转型,获取类的DeclaredField.
     */

    @SuppressWarnings("unchecked")

    protected Field getDeclaredField(final Class clazz, final String fieldName) {
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }
}
