package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Homepage extends AppCompatActivity {
Button call,contacts,conn;
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_homepage);
        conn=findViewById(R.id.ConnDevices);
        call = (Button) findViewById(R.id.CallLogs);
        contacts = (Button) findViewById(R.id.Contacts);
        Intent intent = getIntent();
       mail=intent.getStringExtra("mail");
        System.out.println(mail);
        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println( mail);
                Intent i = new Intent(getApplicationContext(),ShowCallLogs.class);
                i.putExtra("mail",mail);
                startActivity(i);
            }

        });
        contacts.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),fetch_contacts.class);
                i.putExtra("mail",mail);
                startActivity(i);
            }
        });
        conn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(),ShowConnectedEmails.class);
                i.putExtra("mail",mail);
                startActivity(i);
            }
        });

    }
}