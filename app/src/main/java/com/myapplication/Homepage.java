package com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class Homepage extends AppCompatActivity {
Button call,contacts,conn,connect;
EditText email,pass;
FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homepage);
        conn=findViewById(R.id.ConnDevices);
        connect = (Button) findViewById(R.id.button2);
email = (EditText) findViewById(R.id.emailid);
pass = (EditText) findViewById(R.id.password);
        call = (Button) findViewById(R.id.CallLogs);
        contacts = (Button) findViewById(R.id.Contacts);

        Intent intent = getIntent();
       mail=intent.getStringExtra("mail");
        System.out.println(mail);

        connect.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty()||pass.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter email and password to continue", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(!(email.getText().toString().isEmpty())&&!(pass.getText().toString().isEmpty())){
                    try{
                        mAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnSuccessListener(
                                new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        //  mProgressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(),
                                                "Signed In successfully", Toast.LENGTH_SHORT).show();
                                        System.out.println(mAuth.getCurrentUser().getEmail());
                                        FirebaseMessaging.getInstance().getToken()
                                                .addOnCompleteListener(new OnCompleteListener<String>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<String> task) {
                                                        if (!task.isSuccessful()) {
                                                            Log.w("dljaljdas", "Fetching FCM registration token failed", task.getException());
                                                            return;
                                                        }

                                                        // Get new FCM registration token
                                                        String token = task.getResult().toString();

                                                        // Log and toast
                                                        String msg = token;
                                                        System.out.println("token: "+token);

                                                    }
                                                });

                                        Intent i = new Intent(getApplicationContext(),navigationbar.class);
                                        mail=email.getText().toString();
                                        i.putExtra("mail",mail);

                                        startActivity(i);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getApplicationContext(),
                                        "Error signing in " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }catch(Exception e){
                        System.out.println(e);
                    }
                }

            }
        });
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
                Intent i = new Intent( getApplicationContext(),ShowSms.class);
                i.putExtra("mail",mail);
                startActivity(i);
            }
        });

    }
}