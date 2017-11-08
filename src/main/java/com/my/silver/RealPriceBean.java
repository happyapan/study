package com.my.silver;


import java.io.Serializable;

public class RealPriceBean implements Serializable{

    private RealPriceDetailBean ret;
    private  String retCode;

    private  String retDesc;

    public RealPriceDetailBean getRet() {
        return ret;
    }

    public void setRet(RealPriceDetailBean ret) {
        this.ret = ret;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetDesc() {
        return retDesc;
    }

    public void setRetDesc(String retDesc) {
        this.retDesc = retDesc;
    }
}
