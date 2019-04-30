package com.d4ti.frengkas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.d4ti.frengkas.admin.AdminActivity;
import com.d4ti.frengkas.sharedPreference.SaveSharedPreference;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SaveSharedPreference.getLoggedStatus(getApplicationContext())){
                    if (SaveSharedPreference.getStatus(getApplicationContext()).equals("customer")){
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        finish();
                    }
                }else{
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        }, 3000);
    }
}
