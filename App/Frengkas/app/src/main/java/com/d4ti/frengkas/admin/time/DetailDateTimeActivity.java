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
import android.widget.TextView;

import com.d4ti.frengkas.R;
import com.d4ti.frengkas.adapter.TimeAdapter;
import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Pukul;
import com.d4ti.frengkas.model.Waktu;
import com.d4ti.frengkas.response.PukulResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDateTimeActivity extends AppCompatActivity {

    private TextView tv_date;
    private Button btn_add;
    private RecyclerView rv_time;

    private BaseService baseService;
    private int id_waktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_date_time);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Detail Date Time");

        initComponent();

        setDate();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateTimeActivity.class);
                intent.putExtra("ID_WAKTU", id_waktu);
                startActivity(intent);
            }
        });
    }

    private void setDate() {
        baseService.getDetailWaktu(id_waktu).enqueue(new Callback<Waktu>() {
            @Override
            public void onResponse(Call<Waktu> call, Response<Waktu> response) {
                if (response.isSuccessful()){
                    Waktu waktu = response.body();
                    tv_date.setText(waktu.getDate());
                }
            }

            @Override
            public void onFailure(Call<Waktu> call, Throwable t) {
                t.printStackTrace();
            }
        });

        baseService.getPukul(id_waktu).enqueue(new Callback<PukulResponse>() {
            @Override
            public void onResponse(Call<PukulResponse> call, Response<PukulResponse> response) {
                if (response.isSuccessful()){
                    List<Pukul> pukuls = response.body().getPukuls();
                    if (!pukuls.isEmpty()){
                        rv_time.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        TimeAdapter timeAdapter = new TimeAdapter(getApplicationContext());
                        timeAdapter.setPukuls(pukuls);
                        rv_time.setAdapter(timeAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<PukulResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initComponent() {
        id_waktu = getIntent().getIntExtra("ID_WAKTU", 0);
        baseService = APIUtils.getApiService();

        tv_date = findViewById(R.id.txt_date);
        btn_add = findViewById(R.id.btn_add);
        rv_time = findViewById(R.id.rv_time);
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
        setDate();
    }
}
