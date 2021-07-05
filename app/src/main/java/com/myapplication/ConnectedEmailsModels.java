package com.myapplication;

public class ConnectedEmailsModels {
    String mail,deviceName;
    boolean call,messages,files,contact,always,once;

    public ConnectedEmailsModels(boolean files,boolean contacts,boolean call,boolean messages,boolean file, boolean always,boolean once,String mail,String deviceName) {
        this.files = files;
        this.always=always;
        this.call=call;
        this.contact=contacts;
        this.deviceName=deviceName;
        this.messages=messages;
        this.files=file;
        this.once=once;
        this.mail=mail;


    }
}
