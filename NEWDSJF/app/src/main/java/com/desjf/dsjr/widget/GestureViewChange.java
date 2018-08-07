package com.desjf.dsjr.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.desjf.dsjr.R;
import com.desjf.dsjr.utils.AlertUtil;
import com.desjf.dsjr.utils.PreferenceCache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by  liuxuanyi.
 */

public class GestureViewChange extends View{
    public static final int STATE_REGISTER = 101;
    public static final int STATE_LOGIN = 100;
    private static int panelHeight = 300;
    private int mPanelWidth;
    private Bitmap selectedBitmap;
    private Bitmap unSelectedBitmap;
    private Bitmap selectedBitmapSmall;
    private Bitmap unSelectedBitmapSmall;
    private float pieceWidth;
    private float pieceWidthSmall;
    private float mLineHeight;
    private Paint mPaint;
    private float currX;
    private float currY;
    private List<GestureViewChange.GestureBean> listDatas;
    private List<GestureViewChange.GestureBean> listDatasCopy;
    private GestureViewChange.GestureBean lastGestrue = null;
    private int tryCount;

    private Timer mTimer;
    private TimerTask mTimerTask;

    private boolean mError;
    private String message = "请绘制手势";
    //失败尝试次数
    private int tempCount = 5;

    //剩余等待时间
    private int leftTime = 30;

    //记录是否尝试次数超过限制
    private boolean mTimeout;

    private int minPointNums = 4;

    //设置一个参数记录当前是出于初始化阶段还是使用阶段
    private int stateFlag = STATE_LOGIN;
    private GestureViewChange.GestureCallBack gestureCallBack;
    private Context mContext;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            leftTime--;
            if (leftTime == 0) {
                if (mTimer != null)
                    mTimerTask.cancel();
                mTimeout = false;
                AlertUtil.t(mContext, "请绘制解锁图案");
                mError = false;
                invalidate();
                reset();
                return;
            }

            mError = true;
            invalidate();
        }

    };

    public GestureViewChange(Context context) {
        super(context);
        init(context);
    }

    public GestureViewChange(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GestureViewChange(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GestureViewChange(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mTimer = new Timer();



        try {
            //计算上次失败时间是否与当前时间差30s
            long lastTime = PreferenceCache.getGestureTime();
            Date date = new Date();
            if (lastTime != 0 && (date.getTime() - lastTime) / 1000 < 30) {
                mTimeout = true;
                leftTime = (int) (30 - ((date.getTime() - lastTime) / 1000));
                mTimerTask = new GestureViewChange.MyTimerTask(handler);
                mTimer.schedule(mTimerTask, 0, 1000);
            } else {
                mTimeout = false;
                leftTime = 30;
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }


        try {
            gestureCallBack = (GestureViewChange.GestureCallBack) context;
        } catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement GestureCallBack");
        }

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
//        setBackgroundResource(R.mipmap.bg_gesture);
        listDatas = new ArrayList<>();
        listDatasCopy = new ArrayList<>();

        selectedBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_finger_selected);
        // selectedBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_finger_selected_small);
        unSelectedBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_finger_unselected);

        selectedBitmapSmall = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_finger_selected_small);
        unSelectedBitmapSmall = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_finger_unselected);

        stateFlag = getState();
    }

    public void setGestureCallBack(GestureViewChange.GestureCallBack gestureCallBack) {
        this.gestureCallBack = gestureCallBack;
    }

    //重置一些操作
    private void reset() {
        leftTime = 30;
        tempCount = 5;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heightSize);
        if (widthMode == View.MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == View.MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        mLineHeight = width / 3;
        setMeasuredDimension(width, width + panelHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth = Math.min(w, h);
        pieceWidth = (int) (mLineHeight * 0.6f);
        pieceWidthSmall = (int) (mLineHeight * 0.15f);
        selectedBitmap = Bitmap.createScaledBitmap(selectedBitmap, (int) pieceWidth, (int) pieceWidth, false);
        unSelectedBitmap = Bitmap.createScaledBitmap(unSelectedBitmap, (int) pieceWidth, (int) pieceWidth, false);
        selectedBitmapSmall = Bitmap.createScaledBitmap(selectedBitmapSmall, (int) pieceWidthSmall, (int) pieceWidthSmall, false);
        unSelectedBitmapSmall = Bitmap.createScaledBitmap(unSelectedBitmap, (int) pieceWidthSmall, (int) pieceWidthSmall, false);
    }

    private boolean saveState() {
        SharedPreferences sp = mContext.getSharedPreferences("STATE_DATA", Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("state", stateFlag);
        return edit.commit();
    }

    public int getState() {
        SharedPreferences mSharedPreference = mContext.getSharedPreferences("STATE_DATA", Activity.MODE_PRIVATE);
        return mSharedPreference.getInt("state", STATE_REGISTER);
    }


    public boolean clearCache() {
        SharedPreferences sp = mContext.getSharedPreferences("STATE_DATA", Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("state", STATE_REGISTER);
        stateFlag = STATE_REGISTER;
        invalidate();
        return edit.commit();
    }

    public boolean clearCacheLogin() {
        SharedPreferences sp = mContext.getSharedPreferences("STATE_DATA", Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("state", STATE_LOGIN);
        stateFlag = STATE_LOGIN;
        invalidate();
        return edit.commit();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        GestureViewChange.GestureBean firstGestrue = null;
        GestureViewChange.GestureBean currGestrue = null;

        if (stateFlag == STATE_REGISTER) {
            //绘制上面的提示点  不需要提示点
            drawTipsPoint(canvas);
        } else {
            drawTipsText(canvas);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.drawBitmap(unSelectedBitmap, (float) (mLineHeight * (j + 0.5) - pieceWidth / 2), (float) (mLineHeight * (i + 0.5) - pieceWidth / 2 + panelHeight), mPaint);
            }
        }
        if (!listDatas.isEmpty()) {
            firstGestrue = listDatas.get(0);
            for (int i = 1; i < listDatas.size(); i++) {
                currGestrue = listDatas.get(i);
                canvas.drawLine((float) (mLineHeight * (firstGestrue.getX() + 0.5)), (float) (mLineHeight * (firstGestrue.getY() + 0.5) + panelHeight), (float) (mLineHeight * (currGestrue.getX() + 0.5)), (float) (mLineHeight * (currGestrue.getY() + 0.5) + panelHeight), mPaint);
                firstGestrue = currGestrue;
            }

            lastGestrue = listDatas.get(listDatas.size() - 1);
            canvas.drawLine((float) (mLineHeight * (lastGestrue.getX() + 0.5)), (float) (mLineHeight * (lastGestrue.getY() + 0.5) + panelHeight), currX, currY, mPaint);
            for (GestureViewChange.GestureBean bean : listDatas) {
                canvas.drawBitmap(selectedBitmap, (float) (mLineHeight * (bean.getX() + 0.5) - pieceWidth / 2), (float) (mLineHeight * (bean.getY() + 0.5) + panelHeight - pieceWidth / 2), mPaint);
            }
        }
    }


    //绘制提示语
    private void drawTipsText(Canvas canvas) {
        float widthMiddleX = mPanelWidth / 2;
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        //设置文字的大小
        mPaint.setTextSize(40);
        int widthStr1 = (int) mPaint.measureText("原手势密码");
        if (mError) {
            mPaint.setColor(getResources().getColor(R.color.font));
        } else {
            mPaint.setColor(getResources().getColor(R.color.font));
        }
        float baseX = widthMiddleX - widthStr1 / 2;
        float baseY = panelHeight / 2 + 50;
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offY = fontTotalHeight / 2 - fontMetrics.bottom - 30;
        float newY = baseY + offY;
        canvas.drawText("原手势密码", baseX, newY, mPaint);
        mPaint.setColor(getResources().getColor(R.color.main));
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(10);
    }

    private void drawMessage(Canvas canvas, String message, boolean errorFlag) {
        float widthMiddleX = mPanelWidth / 2;
        float firstY = (float) (panelHeight / 2 - pieceWidthSmall / 2 + pieceWidthSmall * 1.25 + 90);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        //设置文字的大小
        mPaint.setTextSize(40);
        int widthStr1 = (int) mPaint.measureText(message);
        if (errorFlag) {
            mPaint.setColor(getResources().getColor(R.color.font));
        } else {
            mPaint.setColor(getResources().getColor(R.color.font));
        }
        float baseX = widthMiddleX - widthStr1 / 2;
        float baseY = firstY + 40;
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offY = fontTotalHeight / 2 - fontMetrics.bottom - 30;
        float newY = baseY + offY;
        canvas.drawText(message, baseX, newY, mPaint);
        mPaint.setColor(getResources().getColor(R.color.main));
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(10);
    }

    //绘制提示点
    private void drawTipsPoint(Canvas canvas) {
        float widthMiddleX = mPanelWidth / 2;
        float firstX = widthMiddleX - pieceWidthSmall / 4 - pieceWidthSmall / 2 - pieceWidthSmall;
        float firstY = panelHeight / 2 - pieceWidthSmall / 2 - pieceWidthSmall - pieceWidthSmall / 4 - 10;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.drawBitmap(unSelectedBitmapSmall, (float) (firstX + j * (pieceWidthSmall * 1.25)), (float) (firstY + i * (pieceWidthSmall * 1.25)), mPaint);
            }
        }

        if (listDatasCopy != null && !listDatasCopy.isEmpty()) {
            for (GestureViewChange.GestureBean bean : listDatasCopy) {
                canvas.drawBitmap(selectedBitmapSmall, (float) (firstX + bean.getX() * (pieceWidthSmall * 1.25)), (float) (firstY + bean.getY() * (pieceWidthSmall * 1.25)), mPaint);
            }
        } else if (listDatas != null && !listDatas.isEmpty()) {
            for (GestureViewChange.GestureBean bean : listDatas) {
                canvas.drawBitmap(selectedBitmapSmall, (float) (firstX + bean.getX() * (pieceWidthSmall * 1.25)), (float) (firstY + bean.getY() * (pieceWidthSmall * 1.25)), mPaint);
            }
        }

        drawMessage(canvas, "绘制解锁图案", mError);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTimeout) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    if (0 < leftTime && leftTime <= 30) {
                        AlertUtil.t(mContext, "尝试次数达到最大," + leftTime + "s后重试");
                    }

                    return true;
            }
            listDatas.clear();
        }

        //if (event.getY() >= ((mLineHeight * (0 + 0.5) - pieceWidth / 2 + panelHeight))) {

        int x = (int) ((event.getY() - panelHeight) / mLineHeight);
        int y = (int) (event.getX() / mLineHeight);
        currX = event.getX();
        currY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastGestrue = null;
                if (currX >= 0 && currX <= mPanelWidth && currY >= panelHeight && currY <= panelHeight + mPanelWidth) {
                    if (currY <= (x + 0.5) * mLineHeight + pieceWidth / 2 + panelHeight && currY >= (x + 0.5) * mLineHeight - pieceWidth / 2 + panelHeight &&
                            currX <= (y + 0.5) * mLineHeight + pieceWidth / 2 && currX >= (y + 0.5) * mLineHeight - pieceWidth / 2) {
                        if (!listDatas.contains(new GestureViewChange.GestureBean(y, x))) {
                            listDatas.add(new GestureViewChange.GestureBean(y, x));
//                                vibrate.vibrate(50);//震半秒钟
                        }
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (currX >= 0 && currX <= mPanelWidth && currY >= panelHeight && currY <= panelHeight + mPanelWidth) {
                    //缩小响应范围 在此处需要注意的是 x跟currX在物理方向上是反的哦
                    if (currY <= (x + 0.5) * mLineHeight + pieceWidth / 2 + panelHeight && currY >= (x + 0.5) * mLineHeight - pieceWidth / 2 + panelHeight &&
                            currX <= (y + 0.5) * mLineHeight + pieceWidth / 2 && currX >= (y + 0.5) * mLineHeight - pieceWidth / 2) {
                        if (!listDatas.contains(new GestureViewChange.GestureBean(y, x))) {
                            listDatas.add(new GestureViewChange.GestureBean(y, x));
//                                vibrate.vibrate(50);//震半秒钟

                        }
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (lastGestrue != null) {
                    currX = (float) ((lastGestrue.getX() + 0.5) * mLineHeight);
                    currY = (float) ((lastGestrue.getY() + 0.5) * mLineHeight);

                }
                if (stateFlag == STATE_LOGIN) {
                    if (listDatas.equals(loadSharedPrefferenceData())) {
                        mError = false;
                        postListener(true);
                        invalidate();
                        listDatas.clear();
                        return true;
                    } else {
                        if (--tempCount == 0) {//尝试次数达到上限
                            mError = true;
                            mTimeout = true;
                            listDatas.clear();

                            Date date = new Date();
                            PreferenceCache.putGestureTime(date.getTime());

                            mTimerTask = new GestureViewChange.MyTimerTask(handler);
                            mTimer.schedule(mTimerTask, 0, 1000);
                            invalidate();
                            return true;
                        }
                        mError = true;
                        AlertUtil.t(mContext, "手势错误,还可以再输入" + tempCount + "次");
                        listDatas.clear();
                    }
                } else if (stateFlag == STATE_REGISTER) {
                    if (listDatasCopy == null || listDatasCopy.isEmpty()) {
                        if (listDatas.size() < minPointNums) {
                            listDatas.clear();
                            mError = true;
                            AlertUtil.t(mContext, "点数不能小于" + minPointNums + "个");
                            invalidate();
                            return true;
                        }
                        listDatasCopy.addAll(listDatas);
                        listDatas.clear();
                        mError = false;
                        AlertUtil.t(mContext, "请再一次绘制");
                    } else {
                        loadSharedPrefferenceData();
                        if (listDatas.equals(listDatasCopy)) {
                            saveToSharedPrefference(listDatas);
                            mError = false;
                            stateFlag = STATE_LOGIN;
                            postListener(true);
                            saveState();
                        } else {
                            mError = true;
                            AlertUtil.t(mContext, "与上次手势绘制不一致,请重新设置");
                        }
                        listDatas.clear();
//                            listDatasCopy.clear();
                        invalidate();
                        return true;
                    }
                }
                listDatas.clear();
                invalidate();
                break;
        }
        // }
        return true;
    }

    //给接口传递数据
    private void postListener(boolean success) {
        if (gestureCallBack != null) {
            gestureCallBack.gestureVerifySuccessListener(stateFlag, listDatas, success);
        }
    }

    private boolean saveToSharedPrefference(List<GestureViewChange.GestureBean> data) {
        SharedPreferences sp = mContext.getSharedPreferences("GESTURAE_DATA", Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("data_size", data.size()); /*sKey is an array*/
        for (int i = 0; i < data.size(); i++) {
            edit.remove("data_" + i);
            edit.putString("data_" + i, data.get(i).getX() + " " + data.get(i).getY());
        }
        return edit.commit();
    }

    public List<GestureViewChange.GestureBean> loadSharedPrefferenceData() {
        List<GestureViewChange.GestureBean> list = new ArrayList<>();
        SharedPreferences mSharedPreference = mContext.getSharedPreferences("GESTURAE_DATA", Activity.MODE_PRIVATE);
        int size = mSharedPreference.getInt("data_size", 0);

        for (int i = 0; i < size; i++) {
            String str = mSharedPreference.getString("data_" + i, "0 0");
            list.add(new GestureViewChange.GestureBean(Integer.parseInt(str.split(" ")[0]), Integer.parseInt(str.split(" ")[1])));
        }
        return list;
    }

    class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            handler.sendMessage(handler.obtainMessage());
        }

    }

    public class GestureBean {
        private int x;
        private int y;

        @Override
        public String toString() {
            return "GestureBean{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public GestureBean(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            return ((GestureViewChange.GestureBean) o).getX() == x && ((GestureViewChange.GestureBean) o).getY() == y;
        }
    }

    public int getMinPointNums() {
        return minPointNums;
    }

    public void setMinPointNums(int minPointNums) {
        if (minPointNums <= 3)
            this.minPointNums = 3;
        if (minPointNums >= 9)
            this.minPointNums = 9;
    }

    public interface GestureCallBack {
        void gestureVerifySuccessListener(int stateFlag, List<GestureViewChange.GestureBean> data, boolean success);
    }
}
