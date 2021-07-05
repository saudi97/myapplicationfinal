package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowConnectedEmails extends AppCompatActivity {
    String mail;
    TextView h;
    private RecyclerView rv_call_logs;
    private ConnectedEmailsAdapter adapter;
    private ConnectedEmailsModels[] connectedemailArrayList=new ConnectedEmailsModels[]{
    new ConnectedEmailsModels(true,false,false,false,false,true,true,"sljajlas","samsung"),
    new ConnectedEmailsModels(true,false,false,false,false,true,true,"sljajlas","oppo"),
    new ConnectedEmailsModels(true,false,false,false,false,true,true,"sljajlas","Apple"),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_connected_emails);
        rv_call_logs=(RecyclerView) findViewById(R.id.hello);
        Intent intent = getIntent();
        mail=intent.getStringExtra("mail");
        h=(TextView) findViewById(R.id.mail);
        h.setText("ConnectedDevices on "+mail);
//        rv_call_logs.setHasFixedSize(true);
//        rv_call_logs.setLayoutManager(new LinearLayoutManager(this));
//        connectedEmailArrayList= new ArrayList<>();
         adapter = new ConnectedEmailsAdapter(this,connectedemailArrayList);
       rv_call_logs.setHasFixedSize(true);
       rv_call_logs.setLayoutManager(new LinearLayoutManager(this));
        rv_call_logs.setAdapter(adapter);


    }



    }
