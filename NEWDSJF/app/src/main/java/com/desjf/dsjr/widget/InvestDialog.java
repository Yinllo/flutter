package com.desjf.dsjr.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class InvestDialog extends Dialog {
    private TextView tv_cancel;
    public InvestDialog(@NonNull Context context) {
        super(context, R.style.My_Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_invest_dialog);
        tv_cancel = (TextView) findViewById(R.id.tv_invest_choose_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
