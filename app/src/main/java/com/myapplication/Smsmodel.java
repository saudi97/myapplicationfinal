package com.myapplication;
public class Smsmodel {

    private String address;
    private String msg;
    private String date;

    public Smsmodel(String address, String msg, String date) {
        this.address = address;
        this.msg = msg;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}