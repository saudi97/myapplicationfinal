package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dialler extends AppCompatActivity {
Button btndelete;
    Button btnone;
    Button btntwo;
    Button btnthree;
    Button btnfour;
    Button btnfive;
    Button btnsix;
    Button btnseven;
    Button btneight;
    Button btnnine;
    Button btnstar;
    Button btnzero;
    Button btnhash;
    Button btncall;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialler);
        btnone=findViewById(R.id.buttonone);
        btndelete=findViewById(R.id.buttondelete);
        btntwo=findViewById(R.id.buttontwo);
        btnthree=findViewById(R.id.buttonthree);
        btnfour=findViewById(R.id.buttonfour);
        btnfive=findViewById(R.id.buttonfive);
        btnsix=findViewById(R.id.buttonsix);
        btnseven=findViewById(R.id.buttonseven);
        btneight=findViewById(R.id.buttoneight);
        btnnine=findViewById(R.id.buttonnine);
        btnstar=findViewById(R.id.buttonstar);
        btnzero=findViewById(R.id.buttonzero);
        btnhash=findViewById(R.id.buttonhash);
        btncall=findViewById(R.id.buttonhash);
        input=findViewById(R.id.inputnumber);

    }
    public void onbuttonclick(Button button, EditText inputn,String number){
        String cache=input.getText().toString();
        inputn.setText(cache+ number);
    }
    public void one(View v){
        onbuttonclick(btnone,input,"1");

    }
    public void two(View v){
        onbuttonclick(btntwo,input,"2");

    }
    public void three(View v){
        onbuttonclick(btnthree,input,"3");

    }
    public void four(View v){
        onbuttonclick(btnfour,input,"4");

    }
    public void five(View v){
        onbuttonclick(btnfive,input,"5");

    }
    public void six(View v){
        onbuttonclick(btnsix,input,"6");

    }
    public void seven(View v){
        onbuttonclick(btnseven,input,"7");

    }
    public void eight(View v){
        onbuttonclick(btneight,input,"8");

    }
    public void nine(View v){
        onbuttonclick(btnnine,input,"9");

    }
    public void star(View v){
        onbuttonclick(btnstar,input,"*");

    }
    public void zero(View v){
        onbuttonclick(btnzero,input,"0");

    }
    public void hash(View v){
        onbuttonclick(btnhash,input,"#");

    }
    public void call(View v){
        FcmNotificationsSender fcm = new FcmNotificationsSender("/topics/all","Testing",input.getText().toString() ,getApplicationContext(),dialler.this);
        fcm.SendNotifications();

    }
    public void ondelete(View v){
        String text = input.getText().toString();
        input.setText(text.substring(0, text.length() - 1));
    }
}