package com.desjf.dsjr.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.MessageModel;
import com.desjf.dsjr.model.NewsCenterModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class AccountMyMessageAdapter extends BaseQuickAdapter<MessageModel,BaseViewHolder> {
    private Context context;
    private String OPEN_FLG;

    public String getOPEN_FLG() {
        return OPEN_FLG;
    }

    public void setOPEN_FLG(String OPEN_FLG) {
        this.OPEN_FLG = OPEN_FLG;
    }

    public AccountMyMessageAdapter(@Nullable List<MessageModel> data, Context context) {
        super(R.layout.item_account_my_message, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageModel item) {
        helper.setText(R.id.tv_message_time,item.getINS_DATE());
        helper.setText(R.id.tv_message_title,item.getTITLE());
        helper.setText(R.id.tv_message_content,item.getCONTENTS());
//        Log.e("MMMM",item.getMAIL_STATUS());
        ImageView status=helper.getView(R.id.iv_message_status);
        if ("已读".equals(item.getMAIL_STATUS())) {
            status.setVisibility(View.INVISIBLE);

        } else {
            status.setVisibility(View.VISIBLE);
           // holder.name.setTypeface(null, Typeface.BOLD);
        }
        if(getOPEN_FLG()!=null)
        {
            if(getOPEN_FLG().equals("1"))
            {
                status.setVisibility(View.INVISIBLE);

            }else {
                status.setVisibility(View.VISIBLE);
            }
        }

    }

}
