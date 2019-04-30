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
import com.d4ti.frengkas.model.Pukul;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTimeActivity extends AppCompatActivity {

    private EditText et_start, et_end;
    private Button btn_create;

    private BaseService baseService;
    private int id_waktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_time);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Create Time");

        initComponent();

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTime();
            }
        });
    }

    private void createTime() {
        baseService.createPukul(id_waktu, et_start.getText().toString(), et_end.getText().toString()).enqueue(new Callback<Pukul>() {
            @Override
            public void onResponse(Call<Pukul> call, Response<Pukul> response) {
                if (response.isSuccessful()){
                    Toast.makeText(CreateTimeActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Pukul> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initComponent() {
        baseService = APIUtils.getApiService();
        id_waktu = getIntent().getIntExtra("ID_WAKTU", id_waktu);

        et_start = findViewById(R.id.et_timestart);
        et_end = findViewById(R.id.et_timeend);
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
