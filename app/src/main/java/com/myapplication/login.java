package com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.savedstate.SavedStateRegistryOwner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.sql.SQLOutput;

public class login extends AppCompatActivity {
    EditText email,pass;
    Button SignIn,signup;
    TextView t1,t2,t3;
    String mail;
    private FirebaseAuth mAuth;
    // Initialize Firebase Auth
// ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Context context = null;
        super.onCreate(savedInstanceState);
     //   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                                                        mail=mAuth.getCurrentUser().getEmail();
                                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                        DatabaseReference myRef = database.getReference(getemail()).child("token");
                                                        myRef.setValue(token);
                                                    }
                                                });

                                        Intent i = new Intent(getApplicationContext(),Homepage.class);
                                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("mail",mAuth.getCurrentUser().getEmail());
                                        editor.commit();
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