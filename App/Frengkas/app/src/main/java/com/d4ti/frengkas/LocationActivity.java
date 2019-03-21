package com.d4ti.frengkas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.d4ti.frengkas.apiHelper.APIUtils;
import com.d4ti.frengkas.apiHelper.BaseService;
import com.d4ti.frengkas.model.Order;
import com.d4ti.frengkas.model.Pukul;
import com.d4ti.frengkas.model.Waktu;
import com.d4ti.frengkas.response.PukulResponse;
import com.d4ti.frengkas.response.WaktuResponse;
import com.d4ti.frengkas.sharedPreference.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationActivity extends AppCompatActivity {

    private EditText et_location;
    private Spinner spinnerDate, spinnerTime;
    private Button btnPesan;

    private List<Waktu> waktus;
    private List<Pukul> pukuls;

    private ArrayList<String> spnWaktu;
    private ArrayList<String> spnPukul;
    private BaseService baseService;

    private int id_user, id_category, id_pukul;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Lokasi Anda");

        id_category = getIntent().getIntExtra("ID_CATEGORY", 0);
        id_user = SaveSharedPreference.getIdUser(this);

        initComponent();
        setSpinnerDate();

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        baseService.createOrder(id_user, id_category, id_pukul, location)
                .enqueue(new Callback<Order>() {
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        if (response.isSuccessful()){
                            Intent nextIntent = new Intent(getApplicationContext(), OrderActivity.class);
                            nextIntent.putExtra("ID_ORDER", 1);
                            startActivity(nextIntent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {
                        Log.e("Error Message", t.getMessage());
                    }
                });
    }

    private void initComponent() {
        baseService = APIUtils.getApiService();
        waktus = new ArrayList<>();
        pukuls = new ArrayList<>();
        spnWaktu = new ArrayList<>();
        spnPukul = new ArrayList<>();
        et_location = findViewById(R.id.et_lokasi);
        spinnerDate = findViewById(R.id.spn_date);
        spinnerTime = findViewById(R.id.spn_time);
        btnPesan = findViewById(R.id.btn_pesan);

        location = et_location.getText().toString();
    }

    public void setSpinnerDate(){
        baseService.getWaktu().enqueue(new Callback<WaktuResponse>() {
            @Override
            public void onResponse(Call<WaktuResponse> call, Response<WaktuResponse> response) {
                if (response.isSuccessful()){
                    waktus.clear();
                    spnWaktu.clear();
                    waktus = response.body().getWaktus();
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, spnWaktu);
                    if (!waktus.isEmpty()){
                        spinnerDate.setAdapter(adapter);
                        for (int i = 0; i< waktus.size(); i++){
                            spnWaktu.add(waktus.get(i).getDate());
                            adapter.notifyDataSetChanged();

                            spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //Toast.makeText(LocationActivity.this, "Seleted " + waktus.get(position).getDate(), Toast.LENGTH_SHORT).show();
                                    baseService.getPukul(waktus.get(position).getId())
                                            .enqueue(new Callback<PukulResponse>() {
                                                @Override
                                                public void onResponse(Call<PukulResponse> call, Response<PukulResponse> response) {
                                                    if (response.isSuccessful()){
                                                        pukuls.clear();
                                                        spnPukul.clear();
                                                        pukuls = response.body().getPukuls();
                                                        final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, spnPukul);
                                                        if (!pukuls.isEmpty()){
                                                            spinnerTime.setAdapter(adapter2);
                                                            for (int j = 0; j<pukuls.size(); j++){
                                                                String pkl = pukuls.get(j).getStart() + " - " +pukuls.get(j).getEnd();
                                                                spnPukul.add(pkl);
                                                                adapter2.notifyDataSetChanged();

                                                                spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                    @Override
                                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                        id_pukul = pukuls.get(position).getId();
                                                                    }

                                                                    @Override
                                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                                    }
                                                                });
                                                            }
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<PukulResponse> call, Throwable t) {
                                                    Log.e("Error Message", t.getMessage());
                                                }
                                            });
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

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
