package com.d4ti.frengkas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Pukul;
import com.d4ti.frengkas.model.Waktu;
import com.d4ti.frengkas.response.WaktuResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationActivity extends AppCompatActivity {

    private EditText et_location;
    private Spinner spinnerDate, spinnerTime;
    private List<Waktu> waktus;
    private List<Pukul> pukuls;

    private ArrayList<String> spnWaktu;
    private BaseService baseService;

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
        setSpinnerDate();
    }

    private void initComponent() {
        baseService = APIUtils.getApiService();
        waktus = new ArrayList<>();
        pukuls = new ArrayList<>();
        spnWaktu = new ArrayList<>();
        et_location = findViewById(R.id.et_lokasi);
        spinnerDate = findViewById(R.id.spn_date);
        spinnerTime = findViewById(R.id.spn_time);
    }

    public void setSpinnerDate(){
        baseService.getWaktu().enqueue(new Callback<WaktuResponse>() {
            @Override
            public void onResponse(Call<WaktuResponse> call, Response<WaktuResponse> response) {
                if (response.isSuccessful()){
                    waktus = response.body().getWaktus();
                    if (!waktus.isEmpty()){
                        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, spnWaktu);
                        spinnerDate.setAdapter(adapter);
                        for (int i = 0; i< waktus.size(); i++){
                            spnWaktu.add(waktus.get(0).getDate());
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WaktuResponse> call, Throwable t) {
                Log.e("Error Message", t.getMessage());
            }
        });
    }
}
