package com.d4ti.frengkas.admin.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.adapter.ServiceAdapter;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Service;
import com.d4ti.frengkas.response.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity {

    private RecyclerView rv_service;
    private BaseService baseService;
    private Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Service");

        initComponent();
        getService();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateServiceActivity.class));
            }
        });
    }

    public void initComponent(){
        rv_service = findViewById(R.id.rv_service);
        baseService = APIUtils.getApiService();
        btn_add = findViewById(R.id.btn_add);
    }

    public void getService(){
        baseService.getService().enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if (response.isSuccessful()){
                    List<Service> services = response.body().getServices();
                    if (!services.isEmpty()){
                        rv_service.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        ServiceAdapter serviceAdapter = new ServiceAdapter(getApplicationContext());
                        serviceAdapter.setServices(services);
                        rv_service.setAdapter(serviceAdapter);
                        Log.i("Service Name", services.get(0).getName());
                    }else {
                        Log.i("Service Data", "Data Empty");
                    }
                }else{
                    Log.e("Service Error", response.message());
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Log.e("Api Error", "Cant load data base");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getService();
    }
}
