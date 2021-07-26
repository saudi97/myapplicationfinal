package com.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class callfragment extends Fragment {

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

    public callfragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_callfragment, container, false);
        btnone=v.findViewById(R.id.buttonone);
        btndelete=v.findViewById(R.id.buttondelete);
        btntwo=v.findViewById(R.id.buttontwo);
        btnthree=v.findViewById(R.id.buttonthree);
        btnfour=v.findViewById(R.id.buttonfour);
        btnfive=v.findViewById(R.id.buttonfive);
        btnsix=v.findViewById(R.id.buttonsix);
        btnseven=v.findViewById(R.id.buttonseven);
        btneight=v.findViewById(R.id.buttoneight);
        btnnine=v.findViewById(R.id.buttonnine);
        btnstar=v.findViewById(R.id.buttonstar);
        btnzero=v.findViewById(R.id.buttonzero);
        btnhash=v.findViewById(R.id.buttonhash);
        btncall=v.findViewById(R.id.buttoncall);
        input=v.findViewById(R.id.inputnumber);
        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one(v);
            }
        });
        btntwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two(v);
            }
        });
        btnthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three(v);
            }
        });
        btnfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                four(v);
            }
        });
        btnfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                five(v);
            }
        });
        btnsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                six(v);
            }
        });
        btnseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seven(v);
            }
        });
        btneight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eight(v);
            }
        });
        btnnine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nine(v);
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ondelete(v);
            }
        });
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(v);
            }
        });
        btnstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star(v);
            }
        });
        btnhash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hash(v);
            }
        });
        btnzero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zero(v);
            }
        });



        return v;
    }
    public void onbuttonclick(Button button, EditText inputn,String number){
        String cache=input.getText().toString();
        inputn.setText(cache+ number);
    }
    private void one(View v){
        onbuttonclick(btnone,input,"1");

    }
    private void two(View v){
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
        if(input.getText().length()<=3){
            Toast.makeText(getActivity().getApplicationContext(),"Please Enter the valid Number",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(Intent.ACTION_CALL);
            String hash=input.getText().toString();
            if (hash.contains("#")){
                hash.replace("#","%23");
            }
            intent.setData(Uri.parse("tel:"+ hash ));
            if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
                //when permission is not granted
                //grand permission
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 100);
            }
            else {
                FcmNotificationsSender fcm = new FcmNotificationsSender("/topics/all","Testing",input.getText().toString() ,getContext(),getActivity());
                fcm.SendNotifications();
            }
        }
    }
    public void ondelete(View v){
        String text = input.getText().toString();
        input.setText(text.substring(0, text.length() - 1));
    }
}