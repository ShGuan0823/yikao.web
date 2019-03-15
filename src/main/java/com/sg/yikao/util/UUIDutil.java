package com.sg.yikao.util;


import java.util.UUID;

/**
 * @Author Ssssg
 * @Description 生成盐
 * @Date 2019/3/15 15:39
 **/

public class UUIDutil {


    private static final int saltLen = 16;

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }


}