package com.myapplication;

import com.google.firebase.auth.FirebaseAuth;

public class AuthService {
    FirebaseAuth auth= FirebaseAuth.getInstance();
   public String getEmail(){
       if(auth.getCurrentUser()==null){
           return "";
       }else {
           return auth.getCurrentUser().getEmail().replace(".","a");
       }

    }
    public boolean SignInEmailAndPassword(String mail,String pass){
       try{

       }catch (Exception e){
           System.out.println(e);
           return false;
       }
       return true;
    }


}
