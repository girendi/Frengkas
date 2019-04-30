package com.d4ti.frengkas.admin.time;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.adapter.DateAdapter;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Waktu;
import com.d4ti.frengkas.response.WaktuResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DateTimeActivity extends AppCompatActivity {

    private Button btn_add;
    private RecyclerView rv_date;
    private BaseService baseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Date Time");

        initComponent();
        setData();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateDateActivity.class));
            }
        });

    }

    private void setData() {
        baseService.getWaktu().enqueue(new Callback<WaktuResponse>() {
            @Override
            public void onResponse(Call<WaktuResponse> call, Response<WaktuResponse> response) {
                if (response.isSuccessful()){
                    List<Waktu> waktus = response.body().getWaktus();
                    if (!waktus.isEmpty()){
                        rv_date.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        DateAdapter dateAdapter = new DateAdapter(getApplicationContext());
                        dateAdapter.setWaktus(waktus);
                        rv_date.setAdapter(dateAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<WaktuResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initComponent() {
        baseService = APIUtils.getApiService();
        btn_add = findViewById(R.id.btn_add);
        rv_date = findViewById(R.id.rv_dateTime);
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
        setData();
    }
}
