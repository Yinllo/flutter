package com.desjf.dsjr.model.bankModel;

/**
 * @Author YinL
 * @Date 2018/4/12 0012
 * @Describe  累计和待收金额Model
 */

public class BankDaiShouModel {


    /**
     * UserEarnings :
     * userDueIn :
     */

    private String UserEarnings;
    private String userDueIn;

    public String getUserEarnings() {
        return UserEarnings == null ? "" : UserEarnings;
    }

    public void setUserEarnings(String userEarnings) {
        UserEarnings = userEarnings;
    }

    public String getUserDueIn() {
        return userDueIn == null ? "" : userDueIn;
    }

    public void setUserDueIn(String userDueIn) {
        this.userDueIn = userDueIn;
    }
}
