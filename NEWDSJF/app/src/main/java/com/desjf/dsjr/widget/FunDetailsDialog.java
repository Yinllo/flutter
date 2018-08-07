package com.desjf.dsjr.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

import com.desjf.dsjr.R;

/**
 * Created by Administrator on 2017/7/21 0021.
 * 资金明细dialog
 */

public class FunDetailsDialog extends Dialog {
    public FunDetailsDialog(@NonNull Context context) {
        super(context);
    }

    public FunDetailsDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected FunDetailsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_fund_details);
    }
}
