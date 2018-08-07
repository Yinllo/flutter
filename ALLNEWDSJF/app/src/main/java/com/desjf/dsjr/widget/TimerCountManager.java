package com.desjf.dsjr.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;

import com.desjf.dsjr.R;

/**
 * @Author YinL
 * @Date 2018/6/14 0014
 * @Describe  验证码倒计时处理类  使用时注意处理周期问题   onDestroy方法中及时 cancel
 */

public class TimerCountManager extends CountDownTimer{

    private Button button;
    private Context context;

    //参数依次为总时长,和计时的时间间隔
    public TimerCountManager(Context context, Button button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.button=button;
        this.context=context;
    }

    //计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {
        //防止发生空指针等问题
        if(null!=context){
            String time = "重发(" + millisUntilFinished / 1000 + ")秒";
            setButtonInfo(time, "#c1c1c1", false);
        }
    }

    //计时完毕时触发
    @Override
    public void onFinish() {
        if(null!=context) {
            button.setText("重新获取");
            button.setBackgroundResource(R.mipmap.sendcode);
            button.setClickable(true);
        }
    }

    /**
     * 验证按钮在点击前后相关设置
     *
     * @param content 要显示的内容
     * @param color  颜色值
     * @param isClick 是否可点击
     */
    private void setButtonInfo(String content, String color, boolean isClick) {
        button.setText(content);
        button.setBackgroundColor(Color.parseColor(color));
        button.setClickable(isClick);
    }

}
