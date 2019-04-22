package com.d4ti.frengkas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailOrderActivity extends AppCompatActivity {

    private int id_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        initComponent();
    }

    private void initComponent() {
        id_order = getIntent().getIntExtra("ID_ORDER", 0);
    }
}
