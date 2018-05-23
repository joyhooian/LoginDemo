package com.example.joyho.basiccomponents;

public class LogInfo {
    private String emailAdd;
    private String pwd;

    //public LogInfo(){
    //}

    LogInfo(String emailAdd, String pwd){
        this.emailAdd = emailAdd;
        this.pwd = pwd;
    }

    //public void setEmailAdd(String emailAdd){
    //    this.emailAdd = emailAdd;
    //}

    //public void setPwd(String pwd){
    //    this.pwd = pwd;
    //}

    public String getEmailAdd(){
        return emailAdd;
    }

    public String getPwd(){
        return pwd;
    }
}