package com.king.entity;

public class TbSendUser {
    private String name;
    private String password;
    private int guid;
    private int accflag;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getGuid() {
        return guid;
    }
    public void setGuid(int guid) {
        this.guid = guid;
    }

    public int getAccflag() {
        return accflag;
    }
    public void setAccflag(int accflag) {
        this.accflag = accflag;
    }
}
