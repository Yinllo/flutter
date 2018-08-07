package com.desjf.dsjr.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * @Author YinL
 * @Date 2018/4/23 0023
 * @Describe  自定义EditText，用来实现给添加在EditText中的图片的点击事件
 */

@SuppressLint("AppCompatCustomView")
public class EditTextDrawableClick extends EditText{

    private DrawableLeftListener mLeftListener;
    private DrawableRightListener mRightListener;
    private DrawableTopListener mTopListener;
    private DrawableBottomListener mBottomListener;

    private final int DRAWABLE_LEFT = 0;
    private final int DRAWABLE_TOP = 1;
    private final int DRAWABLE_RIGHT = 2;
    private final int DRAWABLE_BOTTOM = 3;

    public EditTextDrawableClick(Context context) {
        super(context);
    }

    public EditTextDrawableClick(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextDrawableClick(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * event.getRawX(),相对于左边界的绝对坐标，以左上角为（0,0）
     * event.getX()，相对于自身的坐标，以该空间的左上角为（0,0）
     * getLeft()，相当于margin,控件左边界相对于父控件的距离
     * getPaddingLeft,相当于padding，控件中元素相对于控件的间距
     * getBounds().width()，获取元素绘制区域的宽度
     * drawableRight.getIntrinsicWidth()，获取drawable的实际宽度
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //监听手指抬起时候的动作
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //首先判断是否被实例化了
            if (mRightListener != null) {
                //getCompoundDrawables() 可以获取一个长度为4的drawable数组，存放drawableLeft，Right，Top，Bottom四个图片资源对象
                Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT];
                //首先确保得到的drawable对象不为空，然后得到本次点击事件的x轴坐标，如果>当前控件宽度-控件右间距-drawable实际展示大小，
                // 那么说明就是点击在这个drawable上面了。其实就是算该drawable最左边的x坐标,drawableRight.getIntrinsicWidth()==drawableRight.getBounds().width()等于图标的宽度
                if (drawableRight != null && event.getX() > getWidth() - getPaddingRight() - drawableRight.getIntrinsicWidth()) {
                    //if (drawableRight != null && event.getRawX() >= (getRight() - drawableRight.getBounds().width())) {
                    mRightListener.onDrawableRightClick(this);
                }
            }
            if (mLeftListener != null) {
                Drawable drawableLeft = getCompoundDrawables()[DRAWABLE_LEFT];
                //getX是相对于控件本身的左上角的x坐标,<=控件左边距+图片对象实际的宽度.这边的getLeft相当于margin,getPaddingLeft相当于padding
                if (drawableLeft != null && event.getX() <= getLeft() + drawableLeft.getIntrinsicWidth()) {
                    //                    if (drawableLeft != null && event.getRawX() <= (getLeft() + drawableLeft.getBounds().width())) {
                    mLeftListener.onDrawableLeftClick(this);
                }
            }
            //自己去理解，比较容易的
            if (mTopListener != null) {
                Drawable drawableTop = getCompoundDrawables()[DRAWABLE_TOP];
                if (drawableTop != null && event.getY() <= getTop() + drawableTop.getIntrinsicHeight()) {
                    mTopListener.onDrawableTopClick(this);
                }
            }
            //自己去理解，比较容易的
            if (mBottomListener != null) {
                Drawable drawableBottom = getCompoundDrawables()[DRAWABLE_BOTTOM];
                if (drawableBottom != null && event.getX() > getHeight() - drawableBottom.getIntrinsicWidth()) {
                    mBottomListener.onDrawableBottomClick(this);
                }
            }

        }
        return super.onTouchEvent(event);
    }

    //相应的控件注册的事件
    public void setDrawableLeftListener(DrawableLeftListener mListener) {
        this.mLeftListener = mListener;
    }

    //相应的控件注册的事件
    public void setDrawableRightListener(DrawableRightListener mListener) {
        this.mRightListener = mListener;
    }

    //相应的控件注册的事件
    public void setDrawableTopListener(DrawableTopListener mListener) {
        this.mTopListener = mListener;
    } //相应的控件注册的事件

    public void setDrawableBottomListener(DrawableBottomListener mListener) {
        this.mBottomListener = mListener;
    }

    /**
     * 回调接口需要，需要复写
     */
    public interface DrawableRightListener {
        void onDrawableRightClick(View view);
    }

    /**
     * 回调接口
     */
    public interface DrawableLeftListener {
        void onDrawableLeftClick(View view);
    }

    /**
     * 回调接口
     */
    public interface DrawableTopListener {
        void onDrawableTopClick(View view);
    }

    /**
     * 回调接口
     */
    public interface DrawableBottomListener {
        void onDrawableBottomClick(View view);
    }

}
