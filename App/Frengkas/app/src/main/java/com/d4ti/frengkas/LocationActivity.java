package com.d4ti.frengkas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Spinner;

public class LocationActivity extends AppCompatActivity {

    private EditText et_location;
    private Spinner spinnerDate, spinnerTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Lokasi Anda");

        initComponent();
    }

    private void initComponent() {
        et_location = findViewById(R.id.et_lokasi);
        spinnerDate = findViewById(R.id.spn_date);
        spinnerTime = findViewById(R.id.spn_time);
    }
}
