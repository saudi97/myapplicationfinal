package com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SMSInboxActivity extends Activity {
    private static final int PERMISSIONS_REQUEST_CODE = 999;
    private int flag = 0;
    int index=0;
    public String mail="";
    String[] appPermissions = {
            Manifest.permission.READ_SMS,

    };
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_m_s_inbox);

        ListView lViewSMS = (ListView) findViewById(R.id.listViewSMS);
if(CheckAndRequestPermission() ){
    if(fetchInbox()!=null)
    {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fetchInbox());
        lViewSMS.setAdapter(adapter);
    }

}

    }

    public ArrayList fetchInbox()
    {
        ArrayList sms = new ArrayList();

        Uri uriSms = Uri.parse("content://sms");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);

        cursor.moveToFirst();
        while  (cursor.moveToNext())
        {
            String address = cursor.getString(1);
            String body = cursor.getString(3);



            sms.add("Contact: "+address+"\n SMS : "+body);
        }
        return sms;

    }
    public boolean CheckAndRequestPermission() {
        //checking which permissions are granted
        List<String> listPermissionNeeded = new ArrayList<>();
        for (String item: appPermissions){
            if(ContextCompat.checkSelfPermission(this, item)!= PackageManager.PERMISSION_GRANTED)
                listPermissionNeeded.add(item);
        }

        //Ask for non-granted permissions
        if (!listPermissionNeeded.isEmpty()){
            ActivityCompat.requestPermissions(this, listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]),
                    PERMISSIONS_REQUEST_CODE);
            return false;
        }
        //App has all permissions. Proceed ahead
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if(grantResults[i]==PackageManager.PERMISSION_DENIED){
                    flag = 1;
                    break;
                }
            }


        }
    }
    private void SendDataToServer(ContactModel contactItem) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(getEmail())
                .child("Contacts").child(String.valueOf(index));
        myRef.setValue(contactItem);



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
}