package com.myapplication;
public class Smsmodel {
 //   private String _id;
    private String address;
    private String msg;
 //   private String _readState; //"0" for have not read sms and "1" for have read sms
    private String date;

    public String getAddress() {
        return address;
    }

    public Smsmodel(String address, String msg, String date) {
        this.address = address;
        this.msg = msg;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}