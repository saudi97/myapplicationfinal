package com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.nfc.Tag;
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

public class Signup extends AppCompatActivity {
EditText email,pass,passconfirm;
Button signup;
String token,mail;
private FirebaseAuth mauth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
     //   this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //  getSupportActionBar().hide();
        email=(EditText) findViewById(R.id.emails);
        pass=(EditText) findViewById(R.id.pass);
        passconfirm=(EditText) findViewById(R.id.passconfirm);
        signup = (Button) findViewById(R.id.signups);
        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(email.getText().toString().isEmpty()||pass.getText().toString().isEmpty()||passconfirm.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Provide Email, password and confirm password",Toast.LENGTH_LONG).show();
                }
                if(email.getText().toString().contains("$")&&email.getText().toString().contains("/")&&email.getText().toString().contains("#")&&email.getText().toString().contains("[")&&email.getText().toString().contains("]")){
Toast.makeText(getApplicationContext(),"Email Cannot contain  $, #, [, ], /,",Toast.LENGTH_LONG).show();
                }  if(pass.getText().toString().isEmpty()!=passconfirm.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password does not Match",Toast.LENGTH_LONG).show();
                }else if(!(email.getText().toString().isEmpty())||!(pass.getText().toString().isEmpty())||!(passconfirm.getText().toString().isEmpty())){
                    mauth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    //  mProgressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),
                                            "Signed Up successfully", Toast.LENGTH_SHORT).show();
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
                                                    mail=mauth.getCurrentUser().getEmail();
                                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                    DatabaseReference myRef = database.getReference(getemail()).child("token");
                                                    myRef.setValue(token);
                                                    }
                                            });
                                    Intent i = new Intent(getApplicationContext(),Homepage.class);
                                      //     mail=mauth.getCurrentUser().getEmail();
                              //      Registerandgettoken();
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
                }
            }
        });


    }

    public String getemail() {

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