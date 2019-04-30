package com.d4ti.frengkas.admin.service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateServiceActivity extends AppCompatActivity {

    private EditText et_name, et_desc;
    private Button btn_create;
    private BaseService baseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Create Service");

        initComponent();

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createService();
            }
        });

    }

    private void createService() {
        baseService.createService(et_name.getText().toString(), et_desc.getText().toString()).enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {
                if (response.isSuccessful()){
                    Toast.makeText(CreateServiceActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initComponent() {
        baseService = APIUtils.getApiService();
        et_name = findViewById(R.id.et_service_name);
        et_desc = findViewById(R.id.et_service_desc);
        btn_create = findViewById(R.id.btn_create);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
