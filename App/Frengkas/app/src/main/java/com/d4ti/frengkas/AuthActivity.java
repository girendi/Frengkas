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

    LoginFragment loginFragment = new LoginFragment();
    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (SaveSharedPreference.getLoggedStatus(this)){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        manager.beginTransaction().replace(R.id.frame_auth, loginFragment).commit();
    }
}
