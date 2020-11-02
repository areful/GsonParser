package common;

import parser.JsonUtils;

/**
 * Created by areful on 2020/10/24.
 */
public class Header {
    private int rspCode;
    private String rspDesc;

    public int getRspCode() {
        return rspCode;
    }

    public void setRspCode(int rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspDesc() {
        return rspDesc;
    }

    public void setRspDesc(String rspDesc) {
        this.rspDesc = rspDesc;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}