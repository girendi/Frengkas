package com.d4ti.frengkas;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.d4ti.frengkas.fragment.LoginFragment;
import com.d4ti.frengkas.fragment.RegisterFragment;
import com.d4ti.frengkas.sharedPreference.SaveSharedPreference;

import java.util.Objects;

public class AuthActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    LoginFragment loginFragment = new LoginFragment();
    RegisterFragment registerFragment = new RegisterFragment();
    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (SaveSharedPreference.getLoggedStatus(this)){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        initComponent();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction().replace(R.id.frame_auth, loginFragment).commit();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction().replace(R.id.frame_auth, registerFragment).commit();
            }
        });
    }

    private void initComponent() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
    }

    @Override
    protected void onStart() {
        super.onStart();
        manager.beginTransaction().replace(R.id.frame_auth, loginFragment).commit();
    }
}
