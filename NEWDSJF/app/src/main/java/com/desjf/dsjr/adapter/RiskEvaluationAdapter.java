package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.RiskEvaluationModel;

import java.util.List;


/**
 * @author YinL
 * @Describe 风险测评  题目适配器    通过 是否选中属性来控制 选中 解决 会出现的复用问题
 * @Date 2018-2-6
 */

public class RiskEvaluationAdapter extends BaseQuickAdapter<RiskEvaluationModel,BaseViewHolder> {

    private Context context;
//    RadioButton radioButton1;

    public RiskEvaluationAdapter(@Nullable List<RiskEvaluationModel> data, Context context) {
        super(R.layout.item_recyclerview_risk_evaluation, data);
        this.context = context;

    }

    @Override
    protected void convert(final BaseViewHolder helper,final RiskEvaluationModel item) {
        //给相应布局添加内容 并给 子布局 中的 钮添加点击事件
        helper.setText(R.id.title,item.getTitle());
//        helper.setText(R.id.radioButton1,item.getOptionOne());
//        helper.setText(R.id.radioButton2,item.getOptionTwo());
//        helper.setText(R.id.radioButton3,item.getOptionThree());
//        helper.setText(R.id.radioButton4,item.getOptionFour());

        /**
         * 必须先清空一下按钮组的 选中状态  否则会因为RecyclerView的复用问题 而出来不能同时点击一个选项的问题（
         * 同时点击，之后的按钮不会出现选中状态）
         */
        RadioGroup radioGroup=helper.getView(R.id.radioGroup);
        radioGroup.clearCheck();

        RadioButton radioButton1=helper.getView(R.id.radioButton1);
        radioButton1.setChecked(item.isOne());
        radioButton1.setText(item.getOptionOne());

        RadioButton radioButton2=helper.getView(R.id.radioButton2);
        radioButton2.setChecked(item.isTwo());
        radioButton2.setText(item.getOptionTwo());

        RadioButton radioButton3=helper.getView(R.id.radioButton3);
        radioButton3.setChecked(item.isThree());
        radioButton3.setText(item.getOptionThree());

        RadioButton radioButton4=helper.getView(R.id.radioButton4);
        radioButton4.setChecked(item.isFour());
        radioButton4.setText(item.getOptionFour());

        //给 子布局 中的 钮添加点击事件
        helper.addOnClickListener(R.id.radioButton1);
        helper.addOnClickListener(R.id.radioButton2);
        helper.addOnClickListener(R.id.radioButton3);
        helper.addOnClickListener(R.id.radioButton4);


        }

}
