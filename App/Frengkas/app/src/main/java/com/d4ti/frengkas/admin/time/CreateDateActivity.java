package com.d4ti.frengkas.admin.time;

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
import com.d4ti.frengkas.model.Waktu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDateActivity extends AppCompatActivity {

    private EditText et_date;
    private Button btn_create;
    private BaseService baseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_date);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Create Date");

        initComponent();

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDate();
            }
        });
    }

    private void createDate() {
        baseService.createWaktu(et_date.getText().toString()).enqueue(new Callback<Waktu>() {
            @Override
            public void onResponse(Call<Waktu> call, Response<Waktu> response) {
                if (response.isSuccessful()){
                    Toast.makeText(CreateDateActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Waktu> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initComponent() {
        baseService = APIUtils.getApiService();
        et_date = findViewById(R.id.et_datetime);
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
