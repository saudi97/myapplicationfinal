package com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.savedstate.SavedStateRegistryOwner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.SQLOutput;

public class login extends AppCompatActivity {
    EditText email,pass;
    Button SignIn,signup;
    TextView t1,t2,t3;
    private FirebaseAuth mAuth;
    // Initialize Firebase Auth
// ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Context context = null;
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        email=(EditText) findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.password);
        SignIn = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        t1 =(TextView) findViewById(R.id.textView);
        SignIn.setOnClickListener(new View.OnClickListener(){

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

                                        Intent i = new Intent(getApplicationContext(),Homepage.class);
                                 String mail=  mAuth.getCurrentUser().getEmail();
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
        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.out.println("vgjgjgj");
                Intent i = new Intent(getApplicationContext(),Signup.class);
                startActivity(i);
            }
        });

    }
}