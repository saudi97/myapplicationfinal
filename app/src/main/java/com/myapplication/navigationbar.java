package com.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class navigationbar extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigationbar);
        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navlistener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new callfragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                    Fragment selectedfragment = null;
                    switch (item.getItemId()) {
                        case R.id.callfragment:
                            selectedfragment = new callfragment();
                            break;
                        case R.id.logfragment:
                            selectedfragment = new logfragment();
                            break;
                        case R.id.contactfragment:
                            selectedfragment = new contactfragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedfragment).commit();
                    return true;
                }
            };
}