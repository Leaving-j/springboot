package com.restful.vo;

/**
 * @program: restfulproject
 * @description: description
 * @author: lw
 * @create: 2018-11-08 15:02
 **/
public class LoginVo {
    private String userName;
    private String pwd;
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
