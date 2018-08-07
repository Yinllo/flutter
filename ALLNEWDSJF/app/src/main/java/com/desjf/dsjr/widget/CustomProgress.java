package com.desjf.dsjr.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.desjf.dsjr.R;

/**
 * @Author YinL
 * @Date 2018/3/14
 * @Describe  自定义进度条
 */

public class CustomProgress extends View {
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    //外圆颜色
    private int mBigCircleColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    //字体颜色
    private int mTextColor;
    //文字信息
    private String mTextDescribe="";
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress;
    // 当前进度
    private int mProgress;
    //大圆
    private Paint mBigPatient;
    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画字体的画笔
    private Paint mTextPaint;


    public CustomProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        //初始化变量
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgress, 0, 0);
        mRadius = typeArray.getDimension(R.styleable.CustomProgress_radius, 300);
        mStrokeWidth = typeArray.getDimension(R.styleable.CustomProgress_strokeWidth, 20);
        mCircleColor = typeArray.getColor(R.styleable.CustomProgress_circleColor, Color.BLUE);
        mRingColor = typeArray.getColor(R.styleable.CustomProgress_ringColor, Color.RED);
        mTotalProgress = typeArray.getInt(R.styleable.CustomProgress_totalProgress, 100);
        mTextColor = typeArray.getColor(R.styleable.CustomProgress_textColor, Color.WHITE);
        mBigCircleColor = typeArray.getColor(R.styleable.CustomProgress_bigCircleColor, Color.WHITE);
        //注意这里要释放掉
        typeArray.recycle();
        // 圆环半径
        mRingRadius = mRadius + mStrokeWidth / 2;
    }

    private void initVariable() {
        //内圆画笔
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mCirclePaint.setStyle(Paint.Style.FILL);

        //外圆环
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStrokeCap(Paint.Cap.ROUND);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);

        //文字画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        //文字颜色
        mTextPaint.setColor(mTextColor);
        //文字大小  自己调整来适应圆环大小
//        mTextPaint.setTextSize(30);
        mTextPaint.setTextSize(mRadius/3+4);

        mBigPatient = new Paint();
        mBigPatient.setColor(mBigCircleColor);
        mBigPatient.setAntiAlias(true);
        mBigPatient.setStyle(Paint.Style.FILL);

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;

        canvas.drawCircle(mXCenter, mYCenter, mRadius + mStrokeWidth, mBigPatient);
        canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);

        //绘制进度值和文字描述内容
        if (mProgress >= 0) {
            RectF oval = new RectF();
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            canvas.drawArc(oval, -90, ((float) mProgress / mTotalProgress) * 360, false, mRingPaint); //

//            String txt = (int) (mProgress * 1.0f / mTotalProgress * 100) + "%";
            //要绘制的文字内容
            String txt =mTextDescribe;
            //文字宽度
            mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
            /** 绘制文字
             * tet:要绘制的文字
             * x：绘制原点x坐标
             * y：绘制原点y坐标
             * paint:用来做画的画笔
             */
            canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4, mTextPaint);

        }
    }

    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();
    }

    public void setText(String describe){
        this.mTextDescribe=describe;
    }

    public void setmTotalProgress(int totalProgress) {
        mTotalProgress = totalProgress;
    }
}
