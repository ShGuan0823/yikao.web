package com.sg.yikao.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;



/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 19:28
 **/


@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable{
    private int code;
    private String msg;
    private T data;

    private ServerResponse(int code)
    {
        this.code = code;
    }

    private ServerResponse(int code, T data)
    {
        this.code = code;
        this.data = data;
    }

    private ServerResponse(int code, String msg, T data)
    {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    private ServerResponse(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess()
    {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public int getCode()
    {
        return this.code;
    }

    public String getMsg()
    {
        return this.msg;
    }

    public T getData()
    {
        return (T)this.data;
    }

    public static <T> ServerResponse<T> createBySuccess()
    {
        return new ServerResponse(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg)
    {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data)
    {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data)
    {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> createByError()
    {
        return new ServerResponse(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errMsg)
    {
        return new ServerResponse(ResponseCode.ERROR.getCode(), errMsg);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errCode, String errMsg)
    {
        return new ServerResponse(errCode, errMsg);
    }
}