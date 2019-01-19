package com.king.entity;

public class AtdbUser {
    private String account;
    private String password;
    private int guid;
    private int accflag;

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
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
