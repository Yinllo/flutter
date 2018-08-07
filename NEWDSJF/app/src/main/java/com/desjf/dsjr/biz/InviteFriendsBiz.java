package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.IncomeListModel;
import com.desjf.dsjr.model.IncomeModel;
import com.desjf.dsjr.model.InviteFriendsModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class InviteFriendsBiz {
    //邀请好友
    public static InviteFriendsModel getInvite() throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "inviteInfo", true);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, InviteFriendsModel.class);
    }
}
