package com.sg.yikao.entity;

import java.io.Serializable;

/**
 * @Author Ssssg
 * @Description TODO
 * @Date 2019/3/14 19:10
 **/


public class User implements Serializable{

    // 主键
    private long id;

    // 帐号
    private String username;

    // 密码
    private String password;

    // 邮箱
    private String mail;

    // 手机号
    private String mobile;

    // 盐值
    private String salt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}