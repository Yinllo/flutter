package com.desjf.dsjr.bean;

import com.desjf.dsjr.net.common.BasicResponseBean;
import com.google.gson.annotations.SerializedName;

/**
 * @Author YinL
 * @Date 2018/5/22 0022
 * @Describe
 */

public class RegistBean extends BasicResponseBean{

    /**
     * 1 : 殷亮
     * 2 : 是
     * 3 : banzhuan
     */

    @SerializedName("1")
    private String _$1;
    @SerializedName("2")
    private String _$2;
    @SerializedName("3")
    private String _$3;

    public String get_$1() {
        return _$1;
    }

    public void set_$1(String _$1) {
        this._$1 = _$1;
    }

    public String get_$2() {
        return _$2;
    }

    public void set_$2(String _$2) {
        this._$2 = _$2;
    }

    public String get_$3() {
        return _$3;
    }

    public void set_$3(String _$3) {
        this._$3 = _$3;
    }
}
