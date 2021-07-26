package com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class Signup extends AppCompatActivity {
EditText email,pass,passconfirm;
Button signup;
String token;
private FirebaseAuth mauth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
        email=(EditText) findViewById(R.id.emails);
        pass=(EditText) findViewById(R.id.pass);
        passconfirm=(EditText) findViewById(R.id.passconfirm);
        signup = (Button) findViewById(R.id.signups);
        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(email.getText().toString().isEmpty()||pass.getText().toString().isEmpty()||passconfirm.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Provide Email, password and confirm password",Toast.LENGTH_LONG).show();
                }else if(email.getText().toString().contains("$")&&email.getText().toString().contains("/")&&email.getText().toString().contains("#")&&email.getText().toString().contains("[")&&email.getText().toString().contains("]")){
Toast.makeText(getApplicationContext(),"Email Cannot contain ., $, #, [, ], /,",Toast.LENGTH_LONG).show();
                }else  if(pass.getText().toString().isEmpty()!=passconfirm.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password does not Match",Toast.LENGTH_LONG).show();
                }else if(!(email.getText().toString().isEmpty())||!(pass.getText().toString().isEmpty())||!(passconfirm.getText().toString().isEmpty())){
                    mauth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    //  mProgressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),
                                            "Signed Up successfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(),Homepage.class);
                                    String mail=mauth.getCurrentUser().getEmail();
                                    Registerandgettoken();
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


    public void Registerandgettoken(){
        token = FirebaseMessaging.getInstance().getToken().toString();
        System.out.println("haehahskl"+token);
    }
}