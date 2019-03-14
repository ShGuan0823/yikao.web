package com.sg.yikao.common;

/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 19:29
 **/


public enum ResponseCode {

    SUCCESS(0, "SUCCESS"),  ERROR(1, "ERROR"),  NEED_LOGIN(10, "NEED_LOGIN"),  ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    private ResponseCode(int code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

    public int getCode()
    {
        return this.code;
    }

    public String getDesc()
    {
        return this.desc;
    }
}